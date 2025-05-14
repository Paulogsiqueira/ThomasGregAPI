CREATE OR ALTER PROCEDURE [dbo].[AtualizarLogradouro]
    @Id BIGINT,
    @ClienteId BIGINT,
    @Rua VARCHAR(255),
    @Numero VARCHAR(50),
    @Bairro VARCHAR(100),
    @Cidade VARCHAR(100),
    @Estado VARCHAR(50)
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE Logradouro
    SET
        ClienteId = @ClienteId,
        Rua = @Rua,
        Numero = @Numero,
        Bairro = @Bairro,
        Cidade = @Cidade,
        Estado = @Estado
    OUTPUT
        INSERTED.Id,
        INSERTED.ClienteId,
        INSERTED.Rua,
        INSERTED.Numero,
        INSERTED.Bairro,
        INSERTED.Cidade,
        INSERTED.Estado
    WHERE Id = @Id;
END;