import { Component, OnInit } from '@angular/core';

import { AlDoc, emptyAlDoc } from '../../models/aldoc.model';
import { AldocsService } from '../../services/aldocs.service';

@Component({
  selector: 'aldocs-list',
  templateUrl: './aldocs-list.component.html',
  styleUrls: ['./aldocs-list.component.scss']
})
export class AldocsListComponent implements OnInit {
  aldocs: AlDoc[] = [];
  currentAlDoc: AlDoc;
  currentIndex = -1;

  constructor(private aldocsService: AldocsService) {
    this.currentAlDoc = emptyAlDoc();
  }

  ngOnInit(): void {
    this.retrieveAlDocs();
  }

  retrieveAlDocs(): void {
    this.aldocsService.getAll()
      .subscribe({
        next: (aldocs) => {
          this.aldocs = aldocs;
        },
        error: (e) => console.error(e)
      });
  }

  protected refreshList(): void {
    this.retrieveAlDocs();
    this.currentAlDoc = emptyAlDoc();
    this.currentIndex = -1;
  }

  protected setActiveAlDoc(aldoc: AlDoc, index: number): void {
    this.currentAlDoc = aldoc;
    this.currentIndex = index;
  }
}