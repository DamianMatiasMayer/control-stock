-- Datos de demostración (ficticios). Se cargan en cada arranque.

-- Categorías
INSERT INTO categoria (nombre, descripcion) VALUES ('Almacén', 'Alimentos secos y envasados');
INSERT INTO categoria (nombre, descripcion) VALUES ('Limpieza', 'Artículos de limpieza');
INSERT INTO categoria (nombre, descripcion) VALUES ('Bebidas', 'Bebidas sin alcohol');
INSERT INTO categoria (nombre, descripcion) VALUES ('Perfumería', 'Higiene y cuidado personal');

-- Depósitos
INSERT INTO deposito (nombre, direccion, activo) VALUES ('Central', 'Av. Siempreviva 742', TRUE);
INSERT INTO deposito (nombre, direccion, activo) VALUES ('Sucursal Norte', 'Calle Falsa 123', TRUE);
INSERT INTO deposito (nombre, direccion, activo) VALUES ('Sucursal Sur', 'Pasaje Imaginario 456', TRUE);

-- Productos
INSERT INTO producto (codigo, nombre, activo, categoria_id) VALUES ('ALM-001', 'Yerba 1 kg', TRUE, (SELECT id FROM categoria WHERE nombre = 'Almacén'));
INSERT INTO producto (codigo, nombre, activo, categoria_id) VALUES ('ALM-002', 'Fideos 500 g', TRUE, (SELECT id FROM categoria WHERE nombre = 'Almacén'));
INSERT INTO producto (codigo, nombre, activo, categoria_id) VALUES ('ALM-003', 'Arroz 1 kg', TRUE, (SELECT id FROM categoria WHERE nombre = 'Almacén'));
INSERT INTO producto (codigo, nombre, activo, categoria_id) VALUES ('ALM-004', 'Azúcar 1 kg', TRUE, (SELECT id FROM categoria WHERE nombre = 'Almacén'));
INSERT INTO producto (codigo, nombre, activo, categoria_id) VALUES ('LIM-001', 'Lavandina 1 L', TRUE, (SELECT id FROM categoria WHERE nombre = 'Limpieza'));
INSERT INTO producto (codigo, nombre, activo, categoria_id) VALUES ('LIM-002', 'Detergente 750 ml', TRUE, (SELECT id FROM categoria WHERE nombre = 'Limpieza'));
INSERT INTO producto (codigo, nombre, activo, categoria_id) VALUES ('BEB-001', 'Agua mineral 2 L', TRUE, (SELECT id FROM categoria WHERE nombre = 'Bebidas'));
INSERT INTO producto (codigo, nombre, activo, categoria_id) VALUES ('BEB-002', 'Jugo de naranja 1 L', TRUE, (SELECT id FROM categoria WHERE nombre = 'Bebidas'));
INSERT INTO producto (codigo, nombre, activo, categoria_id) VALUES ('PER-001', 'Jabón de tocador', TRUE, (SELECT id FROM categoria WHERE nombre = 'Perfumería'));
INSERT INTO producto (codigo, nombre, activo, categoria_id) VALUES ('PER-002', 'Shampoo 400 ml', TRUE, (SELECT id FROM categoria WHERE nombre = 'Perfumería'));

-- Existencias: cuánto hay de cada producto en cada depósito, y su stock mínimo
-- Central
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-001'), (SELECT id FROM deposito WHERE nombre = 'Central'), 20, 5);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-002'), (SELECT id FROM deposito WHERE nombre = 'Central'), 40, 10);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-003'), (SELECT id FROM deposito WHERE nombre = 'Central'), 35, 10);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-004'), (SELECT id FROM deposito WHERE nombre = 'Central'), 8, 10);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'LIM-001'), (SELECT id FROM deposito WHERE nombre = 'Central'), 15, 4);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'LIM-002'), (SELECT id FROM deposito WHERE nombre = 'Central'), 18, 6);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'BEB-001'), (SELECT id FROM deposito WHERE nombre = 'Central'), 50, 12);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'PER-001'), (SELECT id FROM deposito WHERE nombre = 'Central'), 30, 8);
-- Sucursal Norte
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-001'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Norte'), 6, 3);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-002'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Norte'), 12, 4);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'LIM-001'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Norte'), 4, 4);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'BEB-001'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Norte'), 24, 6);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'BEB-002'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Norte'), 10, 5);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'PER-001'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Norte'), 9, 3);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'PER-002'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Norte'), 2, 3);
-- Sucursal Sur
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-001'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Sur'), 14, 4);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-003'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Sur'), 9, 4);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'ALM-004'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Sur'), 11, 5);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'LIM-002'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Sur'), 7, 3);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'BEB-002'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Sur'), 3, 5);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'PER-001'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Sur'), 16, 5);
INSERT INTO existencia (producto_id, deposito_id, cantidad, stock_minimo) VALUES ((SELECT id FROM producto WHERE codigo = 'PER-002'), (SELECT id FROM deposito WHERE nombre = 'Sucursal Sur'), 12, 4);