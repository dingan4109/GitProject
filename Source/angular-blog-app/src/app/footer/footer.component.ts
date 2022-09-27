import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
  categories: Array<string> = ['Random things','Life','Eat, Pay & Love'];

  constructor() { }

  ngOnInit(): void {
  }

}
