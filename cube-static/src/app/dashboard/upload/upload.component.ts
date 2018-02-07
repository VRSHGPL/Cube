import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-upload',
    templateUrl: './upload.component.html',
    styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

    filesToUpload: Array<File> = [];
    tmpFilesToUpload: Array<File> = [];

    constructor() { }

    ngOnInit() {
    }

    trackFiles(event: any) {
        this.tmpFilesToUpload = <Array<File>>event.target.files;

        for (var i = 0; i < this.tmpFilesToUpload.length; i++) {
            this.filesToUpload.push(this.tmpFilesToUpload[i]);
        }
    }

    uploadToServer() {
        console.log("uploading to server");
    }
}
