import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class Api {
  private readonly baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getLatestBlock(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/blocks/latest`);
  }

  getBlocks(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/blocks`);
  }

  // ✅ NEW: get block by number
  getBlockByNumber(blockNumber: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/blocks/${blockNumber}`);
  }

  getBlockTransactions(blockNumber: number): Observable<any[]> {
  return this.http.get<any[]>(`${this.baseUrl}/blocks/${blockNumber}/transactions`);
}

  getTransactions(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/transactions`);
  }

  getTransactionByHash(txHash: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/transactions/${txHash}`);
  }

  searchTransactionsByBlock(blockNumber: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/transactions?blockNumber=${blockNumber}`);
  }

  searchTransactionsByAddress(address: string): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.baseUrl}/transactions?address=${encodeURIComponent(address)}`
    );
  }


}
