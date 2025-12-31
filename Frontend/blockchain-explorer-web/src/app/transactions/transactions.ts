import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

import { of } from 'rxjs';
import { catchError, finalize, timeout } from 'rxjs/operators';

import { Api } from '../api';

type Mode = 'all' | 'hash' | 'block' | 'address';

@Component({
  selector: 'app-transactions',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './transactions.html',
  styleUrl: './transactions.scss',
})
export class Transactions implements OnInit {
  mode: Mode = 'all';
  query = '';

  loading = false;
  error = '';
  results: any[] = [];

  constructor(private api: Api, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loadAll();
  }

  reset() {
    this.mode = 'all';
    this.query = '';
    this.loadAll();
  }

  private setLoading(v: boolean) {
    this.loading = v;
    this.cdr.detectChanges();
  }

  loadAll() {
    this.setLoading(true);
    this.error = '';
    this.results = [];
    this.cdr.detectChanges();

    this.api.getTransactions()
      .pipe(
        timeout(8000),
        catchError((err) => {
          console.error('getTransactions failed', err);
          this.error = `Failed to load transactions. (${err?.status ?? 'unknown'})`;
          return of([] as any[]);
        }),
        finalize(() => this.setLoading(false))
      )
      .subscribe((data) => {
        this.results = data ?? [];
        this.cdr.detectChanges();
      });
  }

  search() {
    this.setLoading(true);
    this.error = '';
    this.results = [];
    this.cdr.detectChanges();

    const q = this.query.trim();

    if (this.mode !== 'all' && !q) {
      this.error = 'Please enter a search value.';
      this.setLoading(false);
      return;
    }

    // All
    if (this.mode === 'all') {
      this.loadAll();
      return;
    }

    // Hash (single tx)
    if (this.mode === 'hash') {
      this.api.getTransactionByHash(q)
        .pipe(
          timeout(8000),
          catchError((err) => {
            console.error('getTransactionByHash failed', err);
            this.error =
              err?.status === 404
                ? 'Transaction not found.'
                : `Failed to fetch transaction. (${err?.status ?? 'unknown'})`;
            return of(null);
          }),
          finalize(() => this.setLoading(false))
        )
        .subscribe((tx) => {
          this.results = tx ? [tx] : [];
          this.cdr.detectChanges();
        });

      return;
    }

    // Block (list)
    if (this.mode === 'block') {
      const n = Number(q);
      if (!Number.isFinite(n)) {
        this.error = 'Block number must be a number.';
        this.setLoading(false);
        return;
      }

      this.api.searchTransactionsByBlock(n)
        .pipe(
          timeout(8000),
          catchError((err) => {
            console.error('searchTransactionsByBlock failed', err);
            this.error = `Failed to search by block. (${err?.status ?? 'unknown'})`;
            return of([] as any[]);
          }),
          finalize(() => this.setLoading(false))
        )
        .subscribe((data) => {
          this.results = data ?? [];
          this.cdr.detectChanges();
        });

      return;
    }

    // Address (list)
    this.api.searchTransactionsByAddress(q)
      .pipe(
        timeout(8000),
        catchError((err) => {
          console.error('searchTransactionsByAddress failed', err);
          this.error = `Failed to search by address. (${err?.status ?? 'unknown'})`;
          return of([] as any[]);
        }),
        finalize(() => this.setLoading(false))
      )
      .subscribe((data) => {
        this.results = data ?? [];
        this.cdr.detectChanges();
      });
  }

  txHash(tx: any): string {
    return tx?.txHash ?? tx?.hash ?? tx?.id ?? '';
  }
}
