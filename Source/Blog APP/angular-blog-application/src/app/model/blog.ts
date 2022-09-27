import {Category} from "./category";

export interface Blog {
  id?: number;
  title?: string;
  image?: string;
  description?: string;
  content?: string;
  category?: Category;
  likeNumber?: number;
}
