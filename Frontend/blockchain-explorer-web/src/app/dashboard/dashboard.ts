import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule, JsonPipe, NgIf } from '@angular/common';
import { finalize } from 'rxjs';
import { Api } from '../api';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [JsonPipe, NgIf, CommonModule, RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class Dashboard implements OnInit {
  latestBlock: any = null;
  error: string | null = null;
  loading = true;

  constructor(private api: Api, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.loading = true;

    this.api
      .getLatestBlock()
      .pipe(
        finalize(() => {
          this.loading = false;
          this.cdr.detectChanges(); // force UI update
        })
      )
      .subscribe({
        next: (data) => {
          console.log('Latest block:', data);
          this.latestBlock = data;
          this.error = null;
          this.cdr.detectChanges(); // force UI update
        },
        error: (err) => {
          console.error('Error loading latest block', err);
          this.error = err?.error?.error ?? err?.message ?? 'Request failed';
          this.latestBlock = null;
          this.cdr.detectChanges(); // force UI update
        },
      });
  }
}
