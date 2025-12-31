import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { WatchlistService } from './watchlist.service';

@Component({
  selector: 'app-watchlist',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './watchlist.html',
  styleUrl: './watchlist.scss',
})
export class Watchlist {
  newAddress = '';
  error = '';

  constructor(public watchlist: WatchlistService) {}

  add() {
    this.error = '';
    const addr = this.newAddress.trim();

    if (!addr) {
      this.error = 'Please enter a contract address.';
      return;
    }

    if (addr.length < 6) {
      this.error = 'Address looks too short.';
      return;
    }

    this.watchlist.add(addr);
    this.newAddress = '';
  }

  remove(addr: string) {
    this.watchlist.remove(addr);
  }
}
