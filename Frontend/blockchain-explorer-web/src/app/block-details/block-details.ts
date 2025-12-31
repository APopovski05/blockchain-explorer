import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Api } from '../api';

@Component({
  selector: 'app-block-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './block-details.html',
  styleUrl: './block-details.scss',
})
export class BlockDetailsComponent implements OnInit {
  // ---- Block ----
  block: any = null;
  loading = true;
  error = '';

  // ---- Transactions ----
  transactions: any[] = [];
  txLoading = false;
  txError = '';

  constructor(
    private route: ActivatedRoute,
    private api: Api,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loading = true;
    this.error = '';
    this.block = null;

    const raw = this.route.snapshot.paramMap.get('blockNumber');
    const blockNumber = raw ? Number(raw) : NaN;

    if (!Number.isFinite(blockNumber)) {
      this.loading = false;
      this.error = 'Invalid block number in URL.';
      this.cdr.detectChanges();
      return;
    }

    // ---- Load block details ----
    this.api.getBlockByNumber(blockNumber).subscribe({
      next: (data) => {
        this.block = data;
        this.loading = false;

        // ---- Load transactions AFTER block is loaded ----
        this.txLoading = true;
        this.txError = '';
        this.transactions = [];

        this.api.getBlockTransactions(blockNumber).subscribe({
          next: (txs) => {
            this.transactions = txs ?? [];
            this.txLoading = false;
            this.cdr.detectChanges();
          },
          error: (err) => {
            this.txLoading = false;

            if (err?.status === 404) {
              this.txError =
                'Transactions endpoint not implemented yet (backend returned 404).';
            } else {
              this.txError = 'Failed to load transactions.';
            }

            this.cdr.detectChanges();
          },
        });

        this.cdr.detectChanges();
      },
      error: (err) => {
        this.loading = false;

        if (err?.status === 404) {
          this.error = `Block #${blockNumber} not found.`;
        } else if (err?.status === 401 || err?.status === 403) {
          this.error = 'Unauthorized. API key missing/invalid.';
        } else {
          this.error = 'Failed to load block details.';
        }

        this.cdr.detectChanges();
      },
    });
  }
}
