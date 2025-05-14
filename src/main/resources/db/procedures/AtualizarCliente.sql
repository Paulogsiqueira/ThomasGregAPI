CREATE OR ALTER PROCEDURE [dbo].[AtualizarCliente]
                @Id INT,
                @Nome NVARCHAR(100),
                @Email NVARCHAR(100),
                @Logo VARBINARY(MAX)
            AS
            BEGIN
                SET NOCOUNT ON;

                IF NOT EXISTS (SELECT 1 FROM Cliente WHERE Id = @Id)
                BEGIN
                    RAISERROR('Cliente não encontrado.', 16, 1);
                    RETURN;
                END;

                DECLARE @CurrentEmail NVARCHAR(100);
                SELECT @CurrentEmail = Email FROM Cliente WHERE Id = @Id;

                IF @Email <> @CurrentEmail
                BEGIN
                    IF EXISTS (SELECT 1 FROM Cliente WHERE Email = @Email AND Id <> @Id)
                    BEGIN
                        RAISERROR('O email já está em uso por outro cliente.', 16, 1);
                        RETURN;
                    END;
                END;

                UPDATE Cliente
                SET Nome = @Nome,
                    Email = @Email,
                    Logo = @Logo
                WHERE Id = @Id;

                SELECT Id, Nome, Email, Logo
                FROM Cliente
                WHERE Id = @Id;
            END;
GO