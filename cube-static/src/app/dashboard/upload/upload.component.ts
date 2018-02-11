import { Component, OnInit } from '@angular/core';
import { Http, Response, RequestOptions, Headers } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  filesToUpload: Array<File> = [];
  tmpFilesToUpload: Array<File> = [];


  constructor(private httpClient: HttpClient) { }

  ngOnInit() {
  }

  trackFiles(event: any) {
    this.tmpFilesToUpload = <Array<File>>event.target.files;

    for (var i = 0; i < this.tmpFilesToUpload.length; i++) {
      this.filesToUpload.push(this.tmpFilesToUpload[i]);
    }
  }

  uploadToServer() {

    let formData: FormData = new FormData();
    let headers: Headers = new Headers({ 'Content-Type': 'application/json' });

    if (this.filesToUpload.length > 0) {
      for (var i = 0; i < this.filesToUpload.length; i++) {
        console.log(i);
        formData.append('files' + i, this.filesToUpload[i], this.filesToUpload[i].name);
      }

      this.httpClient.post<any>('/api/file/upload', formData, headers).
        map((response: Response) => {
          console.log(response);
          this.tmpFilesToUpload = [];
          this.filesToUpload = [];
        }).subscribe();
    }
  }
}
