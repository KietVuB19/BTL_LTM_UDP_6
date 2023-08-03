create database btl_ltm_qltaisan;
use btl_ltm_qltaisan;
CREATE TABLE phong (
    ma INT PRIMARY KEY AUTO_INCREMENT,
    ten VARCHAR(255),
    moTa VARCHAR(255)
);

CREATE TABLE taisan (
    ma INT PRIMARY KEY AUTO_INCREMENT,
    ten VARCHAR(255),
    gia FLOAT,
    viTriHienTai VARCHAR(255),
    loaiTaisan VARCHAR(255),
    maPhong INT,
    FOREIGN KEY (maPhong) REFERENCES phong(ma)
);