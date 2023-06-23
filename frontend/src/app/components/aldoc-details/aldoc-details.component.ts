import { Component, Input} from '@angular/core';
import { Router } from '@angular/router';

import { AlDoc, emptyAlDoc } from '../../models/aldoc.model';
import { AldocsService } from '../../services/aldocs.service';

@Component({
  selector: 'aldoc-details',
  templateUrl: './aldoc-details.component.html',
  styleUrls: ['./aldoc-details.component.scss']
})
export class AldocDetailsComponent {
  @Input() currentAlDoc: AlDoc;

  constructor(
    private router: Router,
    private aldocsService: AldocsService) {
    this.currentAlDoc = emptyAlDoc();
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

  download(){
    this.aldocsService.download(this.currentAlDoc.id);
  }
}