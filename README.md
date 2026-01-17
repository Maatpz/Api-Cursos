##  Desafio pratico do rocketseat

###  Finalizando o front end do segundo desafio


## Sobre o Projeto

Sistema CRUD completo que permite cadastrar, visualizar, editar e excluir cursos. O projeto foi desenvolvido em duas etapas: primeiro a construção da API REST, e posteriormente a integração com o frontend utilizando Thymeleaf.

## Tecnologias Utilizadas

**Backend:**
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- H2 Database (banco em memória)

**Frontend:**
- Thymeleaf (template engine)
- Tailwind CSS
- HTML5

## Funcionalidades

- **Listar cursos** - Exibe todos os cursos cadastrados
- **Cadastrar curso** - Formulário para criar novo curso
- **Visualizar detalhes** - Página com informações completas do curso
- **Editar curso** - Atualização dos dados do curso
- **Excluir curso** - Remoção do curso do sistema
- **Ativar/Desativar** - Toggle de status do curso

## Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/cursos` | Lista todos os cursos |
| GET | `/api/cursos/{id}` | Busca curso por ID |
| POST | `/api/cursos/` | Cria novo curso |
| PUT | `/api/cursos/{id}` | Atualiza curso existente |
| DELETE | `/api/cursos/{id}` | Remove curso |

## Rotas do Frontend

| Rota | Descrição |
|------|-----------|
| `/cursos-web/lista` | Página com lista de cursos |
| `/cursos-web/novo` | Formulário de cadastro |
| `/cursos-web/detalhes/{id}` | Detalhes do curso |
| `/cursos-web/editar/{id}` | Formulário de edição |