DROP DATABASE IF EXISTS tienda;
CREATE DATABASE tienda CHARACTER SET utf8mb4;
USE tienda;
CREATE TABLE fabricante (
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL
);
CREATE TABLE producto (
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
precio DOUBLE NOT NULL,
id_fabricante INT UNSIGNED NOT NULL,
FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
);

INSERT INTO fabricante VALUES(1, 'Asus');
INSERT INTO fabricante VALUES(2, 'Lenovo');
INSERT INTO fabricante VALUES(3, 'Hewlett-Packard');
INSERT INTO fabricante VALUES(4, 'Samsung');
INSERT INTO fabricante VALUES(5, 'Seagate');
INSERT INTO fabricante VALUES(6, 'Crucial');
INSERT INTO fabricante VALUES(7, 'Gigabyte');
INSERT INTO fabricante VALUES(8, 'Huawei');
INSERT INTO fabricante VALUES(9, 'Xiaomi');
INSERT INTO producto VALUES(1, 'Disco duro SATA3 1TB', 86.99, 5);
INSERT INTO producto VALUES(2, 'Memoria RAM DDR4 8GB', 120, 6);
INSERT INTO producto VALUES(3, 'Disco SSD 1 TB', 150.99, 4);
INSERT INTO producto VALUES(4, 'GeForce GTX 1050Ti', 185, 7);
INSERT INTO producto VALUES(5, 'GeForce GTX 1080 Xtreme', 755, 6);
INSERT INTO producto VALUES(6, 'Monitor 24 LED Full HD', 202, 1);
INSERT INTO producto VALUES(7, 'Monitor 27 LED Full HD', 245.99, 1);
INSERT INTO producto VALUES(8, 'Portátil Yoga 520', 559, 2);
INSERT INTO producto VALUES(9, 'Portátil Ideapd 320', 444, 2);
INSERT INTO producto VALUES(10, 'Impresora HP Deskjet 3720', 59.99, 3);
INSERT INTO producto VALUES(11, 'Impresora HP Laserjet Pro M26nw', 180, 3);


/*1. Lista el nombre de todos los productos que hay en la tabla producto.*/

select nombre from producto;

/*2. Lista los nombres y los precios de todos los productos de la tabla producto.*/

select nombre, precio from producto;

/*3. Lista todas las columnas de la tabla producto.*/

select * from producto;


/*4. Lista el nombre de los productos, el precio en euros y el precio en dólares estadounidenses (USD).*/

select nombre, precio as precio_euro, precio*0.6 as precio_dolares from producto;


/*5. Lista el nombre de los productos, el precio en euros y el precio en dólares estadounidenses (USD). Utiliza
los siguientes alias para las columnas: nombre de producto, euros, dólares.*/

select nombre as nombre_de_producto, precio as euros, precio*0.6 as dolares from producto;

/*6. Lista los nombres y los precios de todos los productos de la tabla producto, convirtiendo los nombres
a mayúscula.*/

select upper(nombre), precio from producto;

/*7. Lista los nombres y los precios de todos los productos de la tabla producto, convirtiendo los nombres
a minúscula.*/

select lower(nombre), precio from producto;

/*8. Lista el nombre de todos los fabricantes en una columna, y en otra columna obtenga en mayúsculas los
dos primeros caracteres del nombre del fabricante.*/

select nombre, upper(left(nombre,2)) from fabricante;

/*9. Lista los nombres y los precios de todos los productos de la tabla producto, redondeando el valor del
precio.*/

select nombre, round(precio) from producto;

/*10. Lista los nombres y los precios de todos los productos de la tabla producto, truncando el valor del
precio para mostrarlo sin ninguna cifra decimal.*/

select nombre, truncate(precio,0) from producto; 


/*11. Lista el identificador de los fabricantes que tienen productos en la tabla producto.*/

select id_fabricante from producto; 

/*12. Lista el identificador de los fabricantes que tienen productos en la tabla producto, eliminando los iden‑
tificadores que aparecen repetidos.*/
select distinct id_fabricante from producto; 

/*13. Lista los nombres de los fabricantes ordenados de forma ascendente.*/

select nombre from fabricante order by nombre asc;

/*14. Lista los nombres de los fabricantes ordenados de forma descendente.*/

select nombre from fabricante order by nombre desc;


/*15. Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en
segundo lugar por el precio de forma descendente.*/

select nombre from producto order by nombre asc, precio desc;

/*16. Devuelve una lista con las 5 primeras filas de la tabla fabricante.*/

select * from fabricante limit 5;

/*17. Devuelve una lista con 2 filas a partir de la cuarta fila de la tabla fabricante. La cuarta fila también se
debe incluir en la respuesta.*/

select * from fabricante limit 3, 2;

/*18. Lista el nombre y el precio del producto más barato. (Utilice solamente las cláusulas ORDER BY y LIMIT)*/
select nombre, precio from producto order by precio asc limit 1;

/*19. Lista el nombre y el precio del producto más caro. (Utilice solamente las cláusulas ORDER BY y LIMIT)*/

select nombre, precio from producto order by precio desc limit 1;



/*20. Lista el nombre de todos los productos del fabricante cuyo identificador de fabricante es igual a 2.*/

select nombre from producto where id_fabricante=2;


/*21. Lista el nombre de los productos que tienen un precio menor o igual a 120€.*/

select nombre from producto where precio <=120;

/*22. Lista el nombre de los productos que tienen un precio mayor o igual a 400€.*/

select nombre from producto where precio >=400;

/*23. Lista el nombre de los productos que no tienen un precio mayor o igual a 400€*/

select nombre from producto where precio < 400;

/*24. Lista todos los productos que tengan un precio entre 80€ y 300€. Sin utilizar el operador BETWEEN.*/

select * from producto where precio >=80 and precio <=300;

/*25. Lista todos los productos que tengan un precio entre 60€ y 200€. Utilizando el operador BETWEEN.*/

select * from producto where precio between 60 and 200;

/*26. Lista todos los productos que tengan un precio mayor que 200€ y que el identificador de fabricante sea
igual a 6.*/

select * from producto where precio > 200 and id_fabricante = 6;

/*27. Lista todos los productos donde el identificador de fabricante sea 1, 3 o 5. Sin utilizar el operador IN.*/

select * from producto where id_fabricante=1 or id_fabricante=3 or id_fabricante=5 ;

/*28. Lista todos los productos donde el identificador de fabricante sea 1, 3 o 5. Utilizando el operador IN.*/

select * from producto where id_fabricante in (1,3,5);

/*29. Lista el nombre y el precio de los productos en céntimos (Habrá que multiplicar por 100 el valor del precio).
Cree un alias para la columna que contiene el precio que se llame céntimos.*/

select nombre, precio*100 as precio_centimos from producto; 

/*30. Lista los nombres de los fabricantes cuyo nombre empiece por la letra S.*/

select nombre from fabricante where nombre like'S%';

/*31. Lista los nombres de los fabricantes cuyo nombre termine por la vocal e.*/

select nombre from fabricante where nombre like '%e';

/*32. Lista los nombres de los fabricantes cuyo nombre contenga el carácter w.*/

select nombre from fabricante where nombre like '%w%';

/*33. Lista los nombres de los fabricantes cuyo nombre sea de 4 caracteres.*/

select nombre from fabricante where char_length(nombre) = 4;

/*34. Devuelve una lista con el nombre de todos los productos que contienen la cadena Portátil en el nom‑
bre.*/

select nombre from producto where nombre like '%Portátil%';

/*35. Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre
y tienen un precio inferior a 215 €.*/

select nombre from producto where nombre like '%Monitor%' and precio < 215;

/*36. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€. Ordene
el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en
orden ascendente).*/

select nombre, precio from producto where precio >= 180 order by precio desc, nombre asc;




/*MULTITABLAS*/
/*1. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de
la base de datos.*/
/*sql1*/
select p.nombre, p.precio, f.nombre from fabricante f, producto p where p.id_fabricante=f.id;

/*sql2*/
select p.nombre, p.precio, f.nombre from producto p inner join fabricante f on p.id_fabricante=f.id;

/*2. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de
la base de datos. Ordene el resultado por el nombre del fabricante, por orden alfabético.*/
/*sql1*/
select p.nombre, p.precio, f.nombre 
from fabricante f, producto p 
where p.id_fabricante=f.id order by f.nombre asc;
/*sql2*/
select p.nombre, p.precio, f.nombre 
from fabricante f inner join producto p  
on p.id_fabricante=f.id order by f.nombre asc;

/*3. Devuelve una lista con el identificador del producto, nombre del producto, identificador del fabricante y
nombre del fabricante, de todos los productos de la base de datos.*/

/*sql1*/
select p.id, p.nombre, p.id_fabricante, f.nombre 
from fabricante f, producto p 
where p.id_fabricante=f.id;

/*sql2*/

select p.id, p.nombre, p.id_fabricante, f.nombre 
from fabricante f inner join producto p 
on p.id_fabricante=f.id;


/*4. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más barato.*/

/*sql1 */

select p.nombre, p.precio, f.nombre 
from producto p, fabricante f 
where p.id_fabricante = f.id  
order by precio asc limit 1;

/*sql2*/

select p.nombre, p.precio, f.nombre 
from producto p inner join fabricante f 
on p.id_fabricante = f.id  
order by precio asc limit 1;

/*5. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.*/

/*sql1*/
select p.nombre, p.precio, f.nombre 
from producto p, fabricante f 
where p.id_fabricante = f.id  
order by precio desc limit 1;
/*sql2*/
select p.nombre, p.precio, f.nombre 
from producto p inner join fabricante f 
on p.id_fabricante = f.id  
order by precio desc limit 1;

/*6. Devuelve una lista de todos los productos del fabricante Lenovo.*/

/*sql1 */
select p.*, f.nombre 
from producto p, fabricante f 
where p.id_fabricante = f.id 
and f.nombre like '%Lenovo%';

/*sql2*/
select p.*, f.nombre 
from producto p inner join fabricante f 
on p.id_fabricante = f.id 
where f.nombre like '%Lenovo%';



/*7. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.*/
/*sql1*/
select p.*, f.nombre 
from fabricante f, producto p 
where p.id_fabricante=f.id and f.nombre 
like '%Crucial%' and p.precio > 200;

/*sql2*/
select p.*, f.nombre 
from fabricante f inner join producto p 
on p.id_fabricante=f.id where f.nombre 
like '%Crucial%' and p.precio > 200;


/*8. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packardy Seagate.
Sin utilizar el operador IN.*/
/*sql1*/
select p.*, f.nombre 
from fabricante f, producto p 
where p.id_fabricante=f.id or f.nombre 
like '%Asus%' or f.nombre 
like '%Hewlett-Packardy%' or f.nombre 
like '%Seagate%';

/*sql2*/

select p.*, f.nombre 
from fabricante f inner join producto p 
on p.id_fabricante=f.id where f.nombre 
like '%Asus%' or f.nombre 
like '%Hewlett-Packardy%' or f.nombre 
like '%Seagate%';



/*9. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packardy Seagate.
Utilizando el operador IN.*/
/*sql1*/
select p.*, f.nombre 
from fabricante f, producto p 
where p.id_fabricante=f.id and f.nombre 
in('Asus','Hewlett-Packardy','Seagate');

/*sql2*/
select p.*, f.nombre 
from fabricante f inner join producto p 
on p.id_fabricante=f.id where f.nombre 
in('Asus','Hewlett-Packardy','Seagate');

/*10. Devuelve un listado con el nombre y el precio de todos los productos de los fabricantes cuyo nombre
termine por la vocal e.*/
/*sql1*/
select p.nombre, p.precio, f.nombre 
from producto p, fabricante f 
where p.id_fabricante = f.id 
and f.nombre like '%e';
/*sql2*/
select p.nombre, p.precio, f.nombre 
from producto p inner join fabricante f  
on p.id_fabricante = f.id 
where f.nombre like '%e';


/*11. Devuelve un listado con el nombre y el precio de todos los productos cuyo nombre de fabricante contenga
el carácter w en su nombre.*/

/*sql1*/
select p.nombre, p.precio, f.nombre 
from producto p, fabricante f 
where p.id_fabricante = f.id 
and f.nombre like '%w%';
/*sql2*/
select p.nombre, p.precio, f.nombre 
from producto p inner join fabricante f  
on p.id_fabricante = f.id 
where f.nombre like '%w%';
/*12. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos
que tengan un precio mayor o igual a 180€. Ordene el resultado en primer lugar por el precio (en orden
descendente) y en segundo lugar por el nombre (en orden ascendente)*/

/*sql1*/
select p.nombre, p.precio, f.nombre 
from producto p, fabricante f 
where p.id_fabricante=f.id and p.precio >=180 
order by p.precio desc, f.nombre asc;
/*sql2*/
select p.nombre, p.precio, f.nombre 
from producto p inner join fabricante f 
on p.id_fabricante=f.id where p.precio >=180 
order by p.precio desc, f.nombre asc;


/*13. Devuelve un listado con el identificador y el nombre de fabricante, solamente de aquellos fabricantes que
tienen productos asociados en la base de datos.*/
/*sql1*/
select f.id, f.nombre 
from fabricante f, producto p 
where p.id_fabricante = f.id ;

/*sql2*/
select f.id, f.nombre 
from fabricante f inner join producto p 
on p.id_fabricante = f.id;

/*Composición Externa*/

/*1. Devuelve un listado de todos los fabricantes que existen en la base de datos, junto con los productos que
tiene cada uno de ellos. El listado deberá mostrar también aquellos fabricantes que no tienen productos
asociados.*/ 

select f.nombre, p.nombre from fabricante f left join producto p on f.id=p.id_fabricante;

/*2. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.*/

select f.nombre, p.nombre from fabricante f left join producto p on f.id = p.id_fabricante where p.nombre is null;

/*3. ¿Pueden existir productos que no estén relacionados con un fabricante? Justifique su respuesta.*/


/*CONSULTAS RESUMEN*/
/*1. Calcula el número total de productos que hay en la tabla productos.*/
select count(nombre) from producto ;

/*2. Calcula el número total de fabricantes que hay en la tabla fabricante.*/

select count(nombre) from fabricante;

/*3. Calcula el número de valores distintos de identificador de fabricante aparecen en la tabla productos.*/
select count(distinct id_fabricante) from producto;


/*4. Calcula la media del precio de todos los productos.*/

select avg(precio) from producto;


/*5. Calcula el precio más barato de todos los productos.*/

select min(precio) from producto;

/*6. Calcula el precio más caro de todos los productos.*/

select max(precio) from producto;

/*7. Lista el nombre y el precio del producto más barato.*/

select nombre, precio from producto where precio=(select min(precio) from producto);

/*8. Lista el nombre y el precio del producto más caro.*/
select nombre, precio from producto where precio=(select max(precio) from producto);
/*9. Calcula la suma de los precios de todos los productos.*/

select sum(precio) from producto;

/*10. Calcula el número de productos que tiene el fabricante Asus.*/

select count(p.nombre) from producto p, fabricante f where f.id= p.id_fabricante and f.nombre like 'Asus';

/*11. Calcula la media del precio de todos los productos del fabricante Asus.*/

select avg(precio) from producto p inner join fabricante f on p.id_fabricante = f.id where f.nombre like '%Asus%';

/*12. Calcula el precio más barato de todos los productos del fabricante Asus.*/

select min(precio) from producto p inner join fabricante f on p.id_fabricante = f.id where f.nombre like '%Asus%';

/*13. Calcula el precio más caro de todos los productos del fabricante Asus.*/

select max(precio) from producto p inner join fabricante f on p.id_fabricante = f.id where f.nombre like '%Asus%';


/*14. Calcula la suma de todos los productos del fabricante Asus.*/

select sum(precio) from producto p inner join fabricante f on p.id_fabricante = f.id where f.nombre like '%Asus%';


/*15. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fa‑
bricante Crucial.*/

select max(precio) as precio_maximo, min(precio) as precio_minimo, avg(precio) as precio_medio from producto p inner join fabricante f on p.id_fabricante = f.id where f.nombre like '%Crucial%';


/*16. Muestra el número total de productos que tiene cada uno de los fabricantes. El listado también debe
incluir los fabricantes que no tienen ningún producto. El resultado mostrará dos columnas, una con el
nombre del fabricante y otra con el número de productos que tiene. Ordene el resultado descendente‑
mente por el número de productos.*/

SELECT f.nombre, COUNT(p.id_fabricante) AS total_productos
FROM fabricante f
LEFT JOIN producto p ON f.id = p.id_fabricante
GROUP BY f.id, f.nombre
ORDER BY total_productos DESC;

/*17. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes.
El resultado mostrará el nombre del fabricante junto con los datos que se solicitan.*/

select f.nombre, max(p.precio), min(p.precio), avg(p.precio) 
from fabricante f inner join producto p 
on p.id_fabricante= f.id group by f.id, f.nombre;

/*18. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos de los fabricantes
que tienen un precio medio superior a 200€. No es necesario mostrar el nombre del fabricante, con el
identificador del fabricante es suficiente.*/

SELECT p.id_fabricante, 
       MAX(p.precio), 
       MIN(p.precio), 
       AVG(p.precio), 
       COUNT(*) 
FROM producto p
GROUP BY p.id_fabricante
HAVING AVG(p.precio) > 200;

/*19. Muestra el nombre de cada fabricante, junto con el precio máximo, precio mínimo, precio medio y el
número total de productos de los fabricantes que tienen un precio medio superior a 200€. Es necesario
mostrar el nombre del fabricante.*/

select f.nombre, max(p.precio), min(p.precio), avg(p.precio), count(*) 
from producto p inner join fabricante f on p.id_fabricante = f.id group by f.id, f.nombre having avg(p.precio) > 200;


/*20. Calcula el número de productos que tienen un precio mayor o igual a 180€.*/

select count(*) from producto p where precio >=180;

