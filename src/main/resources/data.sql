-- Datos de demostración (ficticios). Se cargan en cada arranque.

-- Categorías
INSERT INTO categoria (nombre, descripcion) VALUES ('Almacén', 'Alimentos secos y envasados');
INSERT INTO categoria (nombre, descripcion) VALUES ('Limpieza', 'Artículos de limpieza');
INSERT INTO categoria (nombre, descripcion) VALUES ('Bebidas', 'Bebidas sin alcohol');

-- Depósitos
INSERT INTO deposito (nombre, direccion, activo) VALUES ('Central', 'Av. Siempreviva 742', TRUE);
INSERT INTO deposito (nombre, direccion, activo) VALUES ('Sucursal Norte', 'Calle Falsa 123', TRUE);

-- Productos
INSERT INTO producto (codigo, nombre, activo, categoria_id)
    VALUES ('ALM-001', 'Yerba 1 kg', TRUE, (SELECT id FROM categoria WHERE nombre = 'Almacén'));
INSERT INTO producto (codigo, nombre, activo, categoria_id)
    VALUES ('ALM-002', 'Fideos 500 g', TRUE, (SELECT id FROM categoria WHERE nombre = 'Almacén'));
INSERT INTO producto (codigo, nombre, activo, categoria_id)
    VALUES ('LIM-001', 'Lavandina 1 L', TRUE, (SELECT id FROM categoria WHERE nombre = 'Limpieza'));
INSERT INTO producto (codigo, nombre, activo, categoria_id)
    VALUES ('BEB-001', 'Agua mineral 2 L', TRUE, (SELECT id FROM categoria WHERE nombre = 'Bebidas'));

-- Existencias: cuánto hay de cada producto en cada depósito, y su stock mínimo
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo)
    VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-001'), (SELECT id FROM deposito WHERE nombre = 'Central'), 20, 5);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo)
    VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-001'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Norte'), 6, 3);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo)
    VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-002'), (SELECT id FROM deposito WHERE nombre = 'Central'), 40, 10);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo)
    VALUES ((SELECT id FROM producto WHERE codigo = 'LIM-001'), (SELECT id FROM deposito WHERE nombre = 'Central'), 15, 4);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo)
    VALUES ((SELECT id FROM producto WHERE codigo = 'LIM-001'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Norte'), 4, 4);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo)
    VALUES ((SELECT id FROM producto WHERE codigo = 'BEB-001'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Norte'), 24, 6);