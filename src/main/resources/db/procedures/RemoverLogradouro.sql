CREATE OR ALTER PROCEDURE [dbo].[RemoverLogradouro]
    @Id BIGINT
AS
BEGIN
    SET NOCOUNT ON;

    DELETE FROM Logradouro
    WHERE Id = @Id;
END;
GO