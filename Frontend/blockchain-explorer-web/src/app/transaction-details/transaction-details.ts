import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Api } from '../api';

@Component({
  selector: 'app-transaction-details',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './transaction-details.html',
  styleUrl: './transaction-details.scss',
})
export class TransactionDetailsComponent implements OnInit {
  tx: any = null;
  loading = true;
  error = '';

  constructor(private route: ActivatedRoute, private api: Api) {}

  ngOnInit(): void {
    const txHash = this.route.snapshot.paramMap.get('txHash') ?? '';

    if (!txHash) {
      this.loading = false;
      this.error = 'Missing transaction hash.';
      return;
    }

    this.api.getTransactionByHash(txHash).subscribe({
      next: (data) => {
        this.tx = data;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        this.error = err?.status === 404 ? 'Transaction not found.' : 'Failed to load transaction.';
      },
    });
  }
}
