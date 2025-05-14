CREATE OR ALTER PROCEDURE [dbo].[RemoverCliente]
    @Id INT
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (SELECT 1 FROM Cliente WHERE Id = @Id)
    BEGIN
        RAISERROR('Cliente não encontrado.', 16, 1);
        RETURN;
    END;

    DELETE FROM Logradouro WHERE ClienteId = @Id;
    DELETE FROM Cliente WHERE Id = @Id;
END;