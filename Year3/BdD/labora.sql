-- 3 CINEMA
-- 1
SELECT cod_pais
FROM PAIS
WHERE nombre IS NOT NULL
ORDER BY cod_pais;

-- 2
SELECT cod_peli, titulo, anyo
FROM Pelicula
WHERE anyo < 1970 AND cod_lib IS NULL
ORDER BY titulo;

-- 3
SELECT cod_act, nombre
FROM Actor
WHERE nombre LIKE 'John%';

-- 4
SELECT cod_peli, titulo
FROM Pelicula
WHERE duracion > 120 AND anyo BETWEEN 1980 AND 1989;

-- 5
SELECT cod_peli, titulo
FROM pelicula
WHERE director LIKE '%Pakula%' AND cod_lib IS NOT NULL;

-- 6
SELECT COUNT(cod_peli)
FROM Pelicula
WHERE duracion > 120 AND anyo BETWEEN 1980 AND 1989;

-- 7
SELECT COUNT  (DISTINCT  P.cod_peli)
FROM Pelicula P, Clasificacion C
WHERE p.cod_peli = C.cod_peli AND (C.cod_gen = 'BB5' OR C.cod_gen = 'GG4' OR C.cod_gen = 'JH6');

-- 8
SELECT MIN (anyo)
FROM Libro_peli;

-- 9
SELECT AVG (duracion)
FROM Pelicula
WHERE anyo = 1987;

-- 10
SELECT SUM (duracion)
FROM Pelicula
WHERE director = 'Steven Spielberg';

-- 11
SELECT P.cod_peli, P.titulo
FROM Pelicula P, Actua T , Actor A
WHERE P.cod_peli = T.cod_peli AND T.cod_act = A.cod_act AND P.director = A.nombre
ORDER BY titulo;

-- 12
SELECT P.cod_peli, P.titulo
FROM Pelicula P, Clasificacion C, Genero G
WHERE P.cod_peli = C.cod_peli AND C.cod_gen = G.cod_gen AND G.nombre = 'Comedia'
ORDER BY P.titulo;

-- 13
SELECT P.cod_peli , P.titulo
FROM Pelicula P, Libro_peli L
WHERE P.cod_lib = L.cod_lib AND L.anyo < 1950
ORDER BY P.titulo;

-- 14
SELECT DISTINCT A.cod_pais, PA.nombre
FROM Pelicula P, Actua T , Actor A, Clasificacion C, Genero G, Pais PA
WHERE P.cod_peli = C.cod_peli AND C.cod_gen = G.cod_gen AND  P.cod_peli = T.cod_peli AND T.cod_act = A.cod_act AND PA.cod_pais = A.cod_pais
    AND G.nombre = 'Comedia' 
ORDER BY PA.nombre;

-- 15_11
SELECT P.cod_peli, P.titulo
FROM Pelicula P
WHERE P.director IN (SELECT AC.nombre FROM Actua A, ACTOR AC WHERE A.cod_act = AC.cod_act AND A.cod_peli = P.cod_peli )
ORDER BY P.titulo;

-- 15_12
SELECT P.cod_peli, P.titulo
FROM Pelicula P
WHERE P.cod_peli IN (SELECT C.cod_peli FROM Clasificacion C  WHERE C.cod_gen IN (SELECT G.cod_gen FROM Genero G WHERE G.nombre = 'Comedia'))
ORDER BY P.titulo;

-- 15_13
SELECT P.cod_peli , P.titulo
FROM Pelicula P
WHERE P.cod_lib IN (SELECT L.cod_lib FROM Libro_peli L WHERE L.anyo < 1950)
ORDER BY P.titulo;

-- 15_14
SELECT DISTINCT PA.cod_pais, PA.nombre
FROM  Pais PA
WHERE cod_pais IN (SELECT A.cod_pais FROM Actor A WHERE A.cod_act IN (SELECT AC.cod_act FROM ACTUA AC WHERE AC.cod_peli IN (SELECT C.cod_peli FROM Clasificacion C WHERE C.cod_gen IN (SELECT cod_gen FROM Genero G WHERE G.nombre = 'Comedia') )) )
ORDER BY PA.nombre;

-- 16
SELECT A.cod_act, A.nombre FROM Actor A WHERE EXTRACT(YEAR FROM a.fecha_nac)< 1950 AND A.cod_act IN (SELECT AC.cod_act FROM Actua AC WHERE AC.papel = 'Principal');

-- 17
SELECT L.cod_lib, L.titulo, L.autor 
FROM Libro_peli L
WHERE L.cod_lib IN (SELECT P.cod_lib FROM Pelicula P WHERE P.anyo BETWEEN 1990 AND 1999)
ORDER BY titulo;

-- 18
SELECT cod_lib, titulo, autor 
FROM Libro_peli 
WHERE cod_lib NOT IN (SELECT cod_lib FROM Pelicula WHERE cod_lib is not null)
ORDER BY titulo;

-- 19 
SELECT nombre
FROM Genero
WHERE cod_gen IN(SELECT  cod_gen FROM Clasificacion WHERE cod_peli NOT IN (SELECT cod_peli From Actua))
ORDER BY NOMBRE

-- 20
SELECT titulo
FROM Libro_lib
WHERE cod_peli IN (SELECT cod_peli FROM Actua WHERE cod_act NOT IN (SELECT cod_act FROM Actor WHERE cod_pais IN (SELECT cod_pais FROM Pais WHERE nombre)) )
ORDER BY titulo;

-- 21 -- RECHECK IT
SELECT Count(*)
FROM Clasificacion JOIN Genero USING(cod_gen)
WHERE nombre = 'Comedia' AND cod_peli IN (SELECT cod_peli FROM Actua WHERE papel = 'Secundario' ); -- I am not sure if it is picking just one actor

-- 22
SELECT MIN(anyo) -- Pick the FIRST movie
FROM Pelicula 
WHERE cod_peli IN (SELECT cod_peli FROM Actua WHERE papel = 'Principal' AND cod_act IN (SELECT cod_act FROM Actor WHERE nombre = 'Jude Law'));

-- 23
SELECT cod_act, nombre
FROM Actor
WHERE fecha_nac IN (Select MIN(fecha_nac) FROM Actor );

-- 24 
SELECT cod_act, nombre, fecha_nac
FROM Actor 
WHERE fecha_nac IN (Select MIN(fecha_nac) FROM Actor WHERE EXTRACT(YEAR FROM fecha_nac) = 1940 );

-- 25
SELECT nombre
FROM Genero
WHERE cod_gen IN ( Select cod_gen FROM Clasificacion JOIN Pelicula using(cod_peli) WHERE duracion = (SELECT MAX(duracion) FROM Pelicula))
Order By nombre;

-- 26
SELECT cod_lib, Libro_peli.titulo
From Libro_peli JOIN Pelicula using(cod_lib)
WHERE cod_peli IN (SELECT cod_peli From Actua JOIN Actor using(cod_act) WHERE cod_pais IN (SELECT cod_pais FROM Pais WHERE nombre = 'España'))
ORDER BY  titulo;

-- 27
SELECT titulo
FROM Pelicula P
WHERE anyo < 1950 AND 1 < (SELECT COUNT(cod_gen) FROM Clasificacion C WHERE C.cod_peli = P.cod_peli ) ;

-- 28
SELECT Count(*)
FROM Pelicula P
WHERE 4 > (SELECT COUNT(cod_act) FROM Actua A  WHERE A.cod_peli = P.cod_peli);

-- 29
SELECT DISTINCT director
FROM Pelicula P1
WHERE 250 < (SELECT SUM(duracion) FROM Pelicula P2 WHERE P1.director = P2.director)
ORDER BY director;

- 30
SELECT DISTINCT EXTRACT(YEAR FROM A1.fecha_nac)
FROM Actor A1
WHERE 3 < (SELECT COUNT(cod_act) FROM Actor A2 WHERE EXTRACT(YEAR FROM A1.fecha_nac) = EXTRACT(YEAR FROM A2.fecha_nac) )
ORDER BY EXTRACT(YEAR FROM A1.fecha_nac);

-- 31 
SELECT  cod_act, nombre
FROM Actor A1
WHERE A1.fecha_nac IN (SELECT MAX(fecha_nac) FROM Actor A2 , Actua AC WHERE   A2.cod_act = AC.cod_act AND AC.cod_peli IN (SELECT cod_peli FROM Clasificacion WHERE cod_gen = 'DD8'));

-- 32
SELECT P.cod_pais, P.nombre
FROM Pais P
WHERE NOT EXISTS (SELECT * FROM Actor A2 WHERE  A2.cod_pais = P.cod_pais AND EXTRACT(YEAR FROM A2.fecha_nac) NOT BETWEEN 1900 AND 2000)
    AND EXISTS (SELECT * FROM Actor A2 WHERE A2.cod_pais = P.cod_pais)
Order By P.nombre;

-- 33
SELECT DISTINCT A.cod_act, A.nombre
FROM Actor A, Actua AC
WHERE NOT EXISTS(SELECT * FROM Actua AC2 WHERE A.cod_act = AC2.cod_act AND AC2.papel <> 'Secundario') AND AC.cod_peli IS NOT NULL AND AC.cod_act = A.cod_act
ORDER BY A.nombre;

-- 34  VIP
SELECT A.cod_act, A.nombre
FROM Actor A
WHERE NOT EXISTS(SELECT P.cod_peli FROM Actua AC, Pelicula P WHERE
                            NOT EXISTS (SELECT * FROM Actua AC2 WHERE A.cod_act = AC2.cod_act AND AC2.cod_peli = AC.cod_peli ) 
                            AND AC.cod_peli = P.cod_peli AND P.director = 'Guy Ritchie') 
    AND EXISTS (SELECT * FROM Pelicula WHERE director = 'Guy Ritchie');

-- 35

SELECT A.cod_act, A.nombre
FROM Actor A
WHERE NOT EXISTS(SELECT P.cod_peli FROM Actua AC, Pelicula P WHERE
                            NOT EXISTS (SELECT * FROM Actua AC2 WHERE A.cod_act = AC2.cod_act AND AC2.cod_peli = AC.cod_peli ) 
                            AND AC.cod_peli = P.cod_peli AND P.director = 'John Steel') 
    AND EXISTS (SELECT * FROM Pelicula WHERE director = 'John Steel');
    
-- 36
SELECT DISTINCT P.cod_peli, P.titulo  
FROM Pelicula P, Actua AC1, Actor A1
WHERE AC1.cod_peli = P.cod_peli AND A1.cod_act = AC1.cod_act AND P.duracion < 100 AND NOT EXISTS (SELECT * FROM Actua AC2, Actor A2 WHERE AC2.cod_act = A2.cod_act AND AC2.cod_peli = P.cod_peli AND A2.cod_pais <> A1.cod_pais)
                                                                                    -- Witch is the addom ?
                                                                                    -- AND EXISTS (SELECT * FROM Actua AC2, Actor A2 WHERE AC2.cod_act = A2.cod_act AND AC2.cod_peli = P.cod_peli)
ORDER BY P.titulo;                                                                                    


-- 37 WORKING ON IT
SELECT DISTINCT P.cod_peli, P.titulo, P.anyo
FROM   Pelicula P, Actua AC
WHERE AC.cod_peli = P.cod_peli AND NOT EXISTS (SELECT A.cod_act FROM Actor A WHERE A.cod_act = AC.cod_act AND EXTRACT(YEAR FROM A.fecha_nac) > 1943)
                                    AND EXISTS (SELECT * FROM Actor A WHERE EXTRACT(YEAR FROM A.fecha_nac) < 1943);
                                
-- 38 WORKING ON IT
SELECT DISTINCt P.cod_pais , P.nombre
FROM Pais P
WHERE  NOT EXISTS (SELECT A.cod_act FROM Actor A, Actua AC, Pelicula PE WHERE A.cod_pais = P.cod_pais AND A.cod_act = AC.cod_act AND AC.cod_peli = PE.cod_peli AND PE.duracion <= 120 )
        AND EXISTS (SELECT * FROM Pelicula PE WHERE PE.duracion > 120)  
ORDER BY P.nombre;        
        
-- 39 
SELECT L.cod_lib, L.titulo, COUNT(P.cod_peli) AS CUÁNTAS
FROM Libro_peli L, Pelicula P
WHERE L.cod_lib = P.cod_lib 
GROUP BY L.cod_lib, L.titulo
HAVING COUNT(P.cod_peli) > 1;

-- 40
SELECT DISTINCT G.cod_gen, G.nombre, COUNT(P.cod_peli), ROUND(AVG(P.duracion))
FROM Pelicula P, Clasificacion C, Genero G
WHERE P.cod_peli = C.cod_peli AND G.cod_gen = C.cod_gen
GROUP BY G.cod_gen, G.nombre
HAVING COUNT(P.cod_peli) > 5;

-- 41
SELECT P.cod_peli, P.titulo, COUNT(G.cod_gen)
FROM Pelicula P, Clasificacion C, Genero G
WHERE P.cod_peli = C.cod_peli AND G.cod_gen = C.cod_gen
GROUP BY P.cod_peli, P.titulo, P.anyo
HAVING P.anyo > 2000;

-- 42
SELECT P.director
FROM Pelicula P
WHERE P.director LIKE '%George%'
GROUP BY P.director
HAVING COUNT(P.cod_peli) = 2;

-- 43
SELECT P.cod_peli, P.titulo, COUNT(AC.cod_act)
FROM Pelicula P, Actua AC, Clasificacion C
WHERE P.cod_peli = AC.cod_peli AND P.cod_peli = C.cod_peli
GROUP BY P.cod_peli, P.titulo
HAVING COUNT(DISTINCT C.cod_gen) = 1;

-- 44
SELECT P.cod_pais, P.nombre, COUNT(DISTINCT A.cod_act)
FROM Pais P, Actor A, ACTUA AC, Pelicula PE
WHERE P.cod_pais = A.cod_pais AND AC.cod_act = A.cod_act AND PE.cod_peli = AC.cod_peli AND PE.anyo BETWEEN 1960 AND 1969
GROUP BY P.cod_pais, P.nombre;

-- 45 WORKING ON IT
SELECT G.cod_gen , G.nombre
FROM Genero G, Clasificacion C
WHERE G.cod_gen = C.cod_gen
Group by  G.cod_gen , G.nombre
HAVING  MAX(C.cod_peli) IN COUNT(C.cod_peli)


-- 50
SELECT P.cod_pais, P.nombre, COUNT(A.cod_act)
FROM Pais P LEFT JOIN Actor A on(P.cod_pais = A.cod_pais)
GROUP BY P.cod_pais, P.nombre;

-- 51
SELECT L.cod_lib, L.titulo, COUNT(P.cod_peli)
FROM Libro_peli L LEFT JOIN  Pelicula P on(P.cod_lib = L.cod_lib)
GROUP BY L.cod_lib, L.titulo, L.anyo
HAVING L.anyo > 1980;

-- 52
SELECT P.cod_pais, P.nombre ,  COUNT(DISTINCT A.cod_act)
FROM Pais P LEFT JOIN ( Actor A JOIN Actua AC  on(A.cod_act = AC.cod_act and AC.papel = 'Secundario')) on(P.cod_pais = A.cod_pais)
GROUP BY P.cod_pais, P.nombre;

-- 53
SELECT P.cod_peli, P.titulo, COUNT(DISTINCT C.cod_gen), COUNT(DISTINCT A.cod_act)
FROM Actua A right join (Pelicula P left join Clasificacion C on (C.cod_peli = P.cod_peli)) on(A.cod_peli = P.cod_peli)
WHERE P.duracion > 140
GROUP BY P.cod_peli, P.titulo



