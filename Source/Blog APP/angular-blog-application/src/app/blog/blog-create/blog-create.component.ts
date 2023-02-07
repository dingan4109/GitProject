import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BlogService} from "../../service/blog.service";
import {Router} from "@angular/router";
import {Category} from "../../model/category";
import {CategoryService} from "../../service/category.service";
import {AngularFireStorage} from "@angular/fire/storage";
import {finalize} from "rxjs/operators";
import {HttpErrorResponse} from "@angular/common/http";
import {NotifierService} from "angular-notifier";

@Component({
  selector: 'app-blog-create',
  templateUrl: './blog-create.component.html',
  styleUrls: ['./blog-create.component.css']
})
export class BlogCreateComponent implements OnInit {
  blogForm: FormGroup;
  categories: Category[] = [];
  currentPage: 0;
  itemsPerPage: 5;
  uploadedImage = null;
  notifier: NotifierService;
  url: string = null;

  constructor(private blogService: BlogService, private fb: FormBuilder, private router: Router, private categoryService: CategoryService, private storage: AngularFireStorage, private notifierService: NotifierService) {
    this.notifier = notifierService;
    this.blogForm = this.fb.group({
      title: ['', [Validators.maxLength(80), Validators.required]],
      description: ['', [Validators.maxLength(130)]],
      image: '',
      content: ['', [Validators.required]],
      category: ['', [Validators.required]],
      likeNumber: 0
    })
  }


  ngOnInit(): void {
    this.getAllCategory();
  }


  createBlog() {
    if (this.uploadedImage !== null) {
      //Upload image and download URL
      const imgName = this.getCurrentDateTime() + this.uploadedImage.name;
      const fileRef = this.storage.ref(imgName);
      this.storage.upload(imgName, this.uploadedImage).snapshotChanges().pipe(
        finalize(() => {
          fileRef.getDownloadURL().subscribe(url => {
            this.blogForm.patchValue({image: url});

            //Set category
            let newBlog = this.blogForm.value;
            this.categoryService.findCategoryById(Number(newBlog.category)).subscribe(category => {
              newBlog.category = category;

              //Create new blog
              this.blogService.createBlog(newBlog).subscribe(
                () => {
                },
                () => {
                },
                () => {
                  this.router.navigateByUrl('/blog-list');
                  this.uploadedImage = null;
                }
              )
            })
          })
        })
      ).subscribe();
    } else {
      let newBlog = this.blogForm.value;
      this.categoryService.findCategoryById(Number(newBlog.category)).subscribe(category => {
        newBlog.category = category;

        //Create new blog
        this.blogService.createBlog(newBlog).subscribe(
          () => {
          },
          (error) => {
            if(error.status == 403) {
              if(window.confirm("This function required login")) {
                this.router.navigateByUrl("/login")
              }else {
                this.router.navigateByUrl('/blog-list');
              }
            }
            if(error.status == 400) {
              console.log(error);
            }
          },
          () => {
            this.router.navigateByUrl('/blog-list')
          }
        )
      })
    }
  }

  private getAllCategory() {
    this.categoryService.getAllCategories(this.currentPage, this.itemsPerPage).subscribe(categories => this.categories = categories.content);
  }

  getCurrentDateTime() {
    return new Date().getTime();
  }

  getImageInfo(event: any) {
    this.uploadedImage = event.target.files[0];
    if(this.uploadedImage) {
      const reader = new FileReader();
      reader.readAsDataURL(this.uploadedImage);
      reader.onload = (e: any) => {
        this.url = e.target.result;
      }
    }
  }
}
