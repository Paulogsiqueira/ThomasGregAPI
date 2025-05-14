CREATE OR ALTER PROCEDURE [dbo].[InserirCliente]
    @Nome NVARCHAR(100),
    @Email NVARCHAR(100),
    @Logo VARBINARY(MAX)
AS
BEGIN
    INSERT INTO Cliente (Nome, Email, Logo)
    VALUES (@Nome, @Email, @Logo);
END;
GO