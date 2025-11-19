INSERT INTO tipo_centro_salud (nombre_tipo)
SELECT 'HOSPITAL'
    WHERE NOT EXISTS (
    SELECT 1 FROM tipo_centro_salud WHERE nombre_tipo = 'HOSPITAL'
);

INSERT INTO tipo_centro_salud (nombre_tipo)
SELECT 'CLINICA'
    WHERE NOT EXISTS (
    SELECT 1 FROM tipo_centro_salud WHERE nombre_tipo = 'CLINICA'
);

