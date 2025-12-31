import { Injectable } from '@angular/core';

const KEY = 'be_watchlist';

@Injectable({ providedIn: 'root' })
export class WatchlistService {
  private list: string[] = this.load();

  getAll(): string[] {
    return this.list;
  }

  add(address: string) {
    const normalized = address.trim();
    if (!normalized) return;

    if (!this.list.includes(normalized)) {
      this.list = [normalized, ...this.list];
      this.save();
    }
  }

  remove(address: string) {
    this.list = this.list.filter(x => x !== address);
    this.save();
  }

  clear() {
    this.list = [];
    this.save();
  }

  private load(): string[] {
    try {
      const raw = localStorage.getItem(KEY);
      if (!raw) return [];
      const parsed = JSON.parse(raw);
      return Array.isArray(parsed) ? parsed : [];
    } catch {
      return [];
    }
  }

  private save() {
    localStorage.setItem(KEY, JSON.stringify(this.list));
  }
}
