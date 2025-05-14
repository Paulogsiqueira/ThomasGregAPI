CREATE OR ALTER PROCEDURE [dbo].[ListarTodosClientes]
AS
BEGIN
    SET NOCOUNT ON;

    SELECT Id, Nome, Email, Logo
    FROM Cliente;
END;
GO