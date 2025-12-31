import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Api } from '../api';

@Component({
  selector: 'app-blocks',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './blocks.html',
  styleUrl: './blocks.scss',
})
export class Blocks implements OnInit {
  blocks: any[] = [];
  loading = true;
  error = '';

  constructor(private api: Api, private cdr: ChangeDetectorRef) {
  console.log('Blocks component constructed');
}


  ngOnInit(): void {
  this.loading = true;
  this.error = '';

  this.api.getBlocks().subscribe({
    next: (data) => {
      this.blocks = data;
      this.loading = false;

      console.log('blocks:', this.blocks);

      // ✅ FORCE UI UPDATE
      this.cdr.detectChanges();
    },
    error: (err) => {
      console.error(err);
      this.error = 'Failed to load blocks.';
      this.loading = false;

      // ✅ FORCE UI UPDATE
      this.cdr.detectChanges();
    }
  });
}


}
