import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BlogService} from "../../service/blog.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Category} from "../../model/category";
import {CategoryService} from "../../service/category.service";
import {finalize} from "rxjs/operators";
import {AngularFireStorage} from "@angular/fire/storage";
import {Blog} from "../../model/blog";

@Component({
  selector: 'app-blog-edit',
  templateUrl: './blog-edit.component.html',
  styleUrls: ['./blog-edit.component.css']
})
export class BlogEditComponent implements OnInit {
  blogForm: FormGroup;
  categories: Category[] = [];
  id: number;
  blog: Blog;
  oldImgLink : string;
  currentPage = 0;
  itemsPerPage = 5;
  uploadedImage = null;

  constructor(private blogService: BlogService, private categoryService: CategoryService, private fb: FormBuilder, private activatedRoute: ActivatedRoute, private router: Router, private storage: AngularFireStorage) { }

  ngOnInit(): void {
    this.getAllCategories();
    this.activatedRoute.paramMap.subscribe(paramMap => {
      this.id = Number(paramMap.get('id'));
      this.blogService.findBlogById(this.id).subscribe(blog => {
        this.blog = blog;
        this.oldImgLink = blog.image;
        this.blogForm = this.fb.group({
          id: [this.id,[Validators.required]],
          title: [blog.title,[Validators.required, Validators.maxLength(80)]],
          image: blog.image,
          description: [blog.description,[Validators.maxLength(130)]],
          content: [blog.content,[Validators.required]],
          category: [blog.category.id,[Validators.required]],
          likeNumber: blog.likeNumber
        });
      });
    });
  }

  getAllCategories() {
    this.categoryService.getAllCategories(this.currentPage, this.itemsPerPage).subscribe(categories => this.categories = categories.content)
  }

  editBlog() {
    if (this.uploadedImage !== null) {
      //Upload image and download URL
      const imgName = this.getCurrentDateTime() + this.uploadedImage.name;
      const fileRef = this.storage.ref(imgName);
      this.storage.upload(imgName, this.uploadedImage).snapshotChanges().pipe(
        finalize(() => {
          fileRef.getDownloadURL().subscribe(url => {
            this.blogForm.patchValue({image: url});

            //Delete old image from firebase
            if(this.blog.image !== '') {
              this.storage.storage.refFromURL(this.blog.image).delete();
            }

            //Set category
            let updateBlog = this.blogForm.value;
            this.categoryService.findCategoryById(Number(updateBlog.category)).subscribe(category => {
              updateBlog.category = category;

              //Update blog
              this.blogService.updateBlog(this.id, updateBlog).subscribe(
                () => {
                },
                () => {
                },
                () => {
                  this.router.navigateByUrl('/blog-view/' + this.id);
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
        this.blogService.updateBlog(this.id,newBlog).subscribe(
          () => {
          },
          () => {
          },
          () => {
            this.router.navigateByUrl('/blog-view/' + this.id)
          }
        )
      })
    }
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
        this.oldImgLink = e.target.result;
      }
    }
  }
}
