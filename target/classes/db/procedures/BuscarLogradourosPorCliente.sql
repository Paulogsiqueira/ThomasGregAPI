CREATE OR ALTER PROCEDURE [dbo].[BuscarLogradourosPorCliente]
    @ClienteId BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT
        Id,
        ClienteId,
        Rua,
        Numero,
        Bairro,
        Cidade,
        Estado
    FROM Logradouro
    WHERE ClienteId = @ClienteId;
END;
GO