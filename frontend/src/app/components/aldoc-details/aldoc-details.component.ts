import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { AlDoc, emptyAlDoc } from '../../models/aldoc.model';
import { AldocsService } from '../../services/aldocs.service';

@Component({
  selector: 'aldoc-details',
  templateUrl: './aldoc-details.component.html',
  styleUrls: ['./aldoc-details.component.scss']
})
export class AldocDetailsComponent implements OnInit {
  @Input() currentAlDoc: AlDoc;

  constructor(
    private aldocsService: AldocsService,
    private route: ActivatedRoute,
    private router: Router) {
    this.currentAlDoc = emptyAlDoc();
  }

  ngOnInit(): void {
    this.getAldoc(this.route.snapshot.params["id"]);
  }

  protected getAldoc(id: string): void {
    this.aldocsService.get(id)
      .subscribe({
        next: (retrievedAlDoc) => {
          this.currentAlDoc = retrievedAlDoc;
        },
        error: (e) => console.error(e)
      });
  }

  protected deleteAlDoc(): void {
    this.aldocsService.delete(this.currentAlDoc.id)
      .subscribe({
        next: () => {
          this.router.navigate(['/aldocs']);
        },
        error: (e) => console.error(e)
      });
  }
}