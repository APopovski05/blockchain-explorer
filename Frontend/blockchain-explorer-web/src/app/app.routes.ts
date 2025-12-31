import { Routes } from '@angular/router';

import { Dashboard } from './dashboard/dashboard';
import { Blocks } from './blocks/blocks';
import { BlockDetailsComponent } from './block-details/block-details';
import { Watchlist } from './watchlist/watchlist';

import { Transactions } from './transactions/transactions';
import { TransactionDetailsComponent } from './transaction-details/transaction-details';

export const routes: Routes = [
  { path: '', component: Dashboard },

  { path: 'blocks', component: Blocks },
  { path: 'blocks/:blockNumber', component: BlockDetailsComponent },

  // IMPORTANT: put /transactions BEFORE /transactions/:txHash
  { path: 'transactions', component: Transactions },
  { path: 'transactions/:txHash', component: TransactionDetailsComponent },

  { path: 'watchlist', component: Watchlist },

  { path: '**', redirectTo: '' },
];
