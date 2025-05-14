CREATE OR ALTER PROCEDURE [dbo].[InserirLogradouro]
    @ClienteId BIGINT,
    @Rua VARCHAR(255),
    @Numero VARCHAR(50),
    @Bairro VARCHAR(100),
    @Cidade VARCHAR(100),
    @Estado VARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO Logradouro (ClienteId, Rua, Numero, Bairro, Cidade, Estado)
    VALUES (@ClienteId, @Rua, @Numero, @Bairro, @Cidade, @Estado);

    SELECT
        Id,
        ClienteId,
        Rua,
        Numero,
        Bairro,
        Cidade,
        Estado
    FROM Logradouro
    WHERE Id = SCOPE_IDENTITY();
END;