create database if not exists test_exam_db;
use test_exam_db;

create table trang_thai(
ma_trang_thai int primary key,
ten_trang_thai varchar(50)
);

create table loai_mat_bang(
ma_loai_mat_bang int primary key,
tem_loai_mat_bang varchar(50)
);

create table mat_bang(
ma_mat_bang varchar(50) primary key,
ma_trang_thai int,
dien_tich double,
so_tang int,
ma_loai_mat_bang int,
gia_tien double, 
ngay_bat_dau date,
ngay_ket_thuc date,
foreign key (ma_trang_thai) references trang_thai(ma_trang_thai),
foreign key (ma_loai_mat_bang) references loai_mat_bang(ma_loai_mat_bang)
);

drop table mat_bang;