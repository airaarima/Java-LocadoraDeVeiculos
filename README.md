
# Java-LocadoraDeVeiculos
## 📋 Funcionalidades do Módulo Veículos

| Categoria                  | Funcionalidade                                                   | Onde está implementado                      |
|:--------------------------|:----------------------------------------------------------------|:--------------------------------------------|
| **Cadastro de Veículo**     | Permitir o cadastro de um veículo com validação de placa única e categoria existente | `incluirVeiculo()` em `MenuVeiculos`       |
| **Validação de Placa**      | Verifica se a placa já existe antes de cadastrar                | `incluirVeiculo()` e `buscarPorPlaca()`    |
| **Validação de Categoria**  | Garante que a categoria informada pelo usuário existe na lista  | `validarCategoriaPorNome()` da `Validacoes`|
| **Exclusão de Veículo**     | Permitir a exclusão de um veículo pelo número da placa          | `excluirVeiculo()` em `MenuVeiculos`       |
| **Restrição de Exclusão**   | Impede excluir veículos com locações ativas                     | `listaLocacoes.existeLocacaoAtivaPorPlaca()`|
| **Edição de Veículo**       | Permitir atualizar os dados de um veículo existente pela placa  | `editarVeiculo()` em `MenuVeiculos`        |
| **Listagem de Veículos**     | Listar veículos na ordem em que foram cadastrados (do início ao fim) | `listarDoInicio()` em `ListaVeiculo`      |
| **Listagem Reversa**        | Listar veículos na ordem inversa (do fim para o início)         | `listarDoFim()` em `ListaVeiculo`          |
| **Buscar por Placa**        | Localizar um veículo na lista através da placa                  | `buscarPorPlaca()` em `ListaVeiculo`       |
| **Editar veículo pela placa**| Atualizar informações de um veículo já cadastrado               | `editar()` em `ListaVeiculo`               |
| **Remover veículo pela placa**| Remover um veículo se não estiver locado                       | `remover()` em `ListaVeiculo`              |
| **Carregar veículos via CSV**| Ler os veículos a partir de um arquivo CSV com categorias válidas | `lerVeiculosCsv()` em `ListaVeiculo`      |
| **Validação de dados via Validacoes** | Utiliza o `Validacoes` para validar categorias e restrições de exclusão | `Validacoes` class                         |
| **Formatação amigável de exibição** | Formato de impressão detalhado dos dados de cada veículo | `toString()` em `Veiculo`                  |
| **Comparação de veículos pela placa** | Comparação de igualdade baseada apenas na placa          | `equals()` em `Veiculo`                    |

## 📋 Funcionalidades do Módulo Categorias

| Categoria                  | Funcionalidade                                                   | Onde está implementado                      |
|:--------------------------|:----------------------------------------------------------------|:--------------------------------------------|
| **Cadastro de Categoria**   | Permitir o cadastro de uma nova categoria com ID único e nome não vazio | `incluirCategoria()` em `MenuCategoria` |
| **Validação de ID**         | Impede cadastrar categorias com IDs já existentes               | `incluirCategoria()` em `MenuCategoria` |
| **Validação de Nome**       | Impede cadastrar ou atualizar categorias com nome vazio         | `incluirCategoria()` e `editarCategoria()` |
| **Edição de Categoria**     | Permitir atualizar o nome de uma categoria pelo ID              | `editarCategoria()` em `MenuCategoria` |
| **Exclusão de Categoria**   | Permitir excluir uma categoria informando o ID                  | `excluirCategoria()` em `MenuCategoria` |
| **Restrição de Exclusão**   | Impede excluir categorias vinculadas a veículos                 | `valid.existeVeiculoComCategoria()` em `MenuCategoria` |
| **Listagem de Categorias**  | Listar todas as categorias existentes com seus IDs e nomes       | `System.out.println(categoriasLista)` → usa `toString()` da `ListaCategoria` |
| **Busca de Categoria por ID** | Localiza uma categoria na lista pelo ID                         | `getById()` em `ListaCategoria` |
| **Edição direta na lista**  | Permitir alterar um objeto categoria armazenado na lista         | `editar()` em `ListaCategoria` |
| **Carregar categorias via CSV** | Ler as categorias a partir de um arquivo CSV e armazenar na lista | `lerCategoriasCsv()` em `ListaCategoria` |
| **Formatação amigável de exibição** | Mostra ID e nome de cada categoria formatados | `toString()` em `Categoria` |
| **Comparação de categorias** | Comparação de igualdade baseada no ID e nome                    | `equals()` em `Categoria` |

## 📋 Funcionalidades do Módulo Locações

| Categoria                         | Funcionalidade                                                              | Onde está implementado                     |
|:----------------------------------|:----------------------------------------------------------------------------|:--------------------------------------------|
| **Cadastrar Locação**              | Permitir o registro de uma nova locação com cliente, veículo, datas e valor | `locarVeiculo()` em `MenuLocacao`           |
| **Validar Disponibilidade**         | Verifica se o veículo já está locado antes de permitir a locação             | `verificarVeiculoDisponivel()` em `ListaLocacao` |
| **Listar Locações Ativas**         | Exibe todas as locações atualmente ativas no sistema                        | `listarLocacoesAtivas()` em `MenuLocacao`   |
| **Devolver Veículo**               | Permitir encerrar (remover) a locação de um veículo                         | `devolverVeiculo()` em `MenuLocacao` e `devolverLocacao()` em `ListaLocacao` |
| **Listar Veículos Disponíveis**    | Exibe todos os veículos que não estão locados                                | `listarVeiculosDisponiveis()` em `MenuLocacao` e `listarTodosVeiculosDisponiveis()` em `ListaLocacao` |
| **Filtrar Veículos Disponíveis**   | Filtrar veículos disponíveis por potência, número de lugares ou categoria    | `filtrarVeiculosDisponiveis()` e seus métodos auxiliares |
| **Ordenar Resultados**              | Permitir ao usuário escolher a ordenação (crescente ou decrescente) nos filtros | Controle feito em `filtrarVeiculosDisponiveis()` com `toString()` e `toStringReverso()` |
| **Filtrar Por Potência**            | Retorna veículos disponíveis com a potência especificada                     | `filtrarVeiculosPorPotencia()` em `ListaLocacao` |
| **Filtrar Por Número de Lugares**   | Retorna veículos disponíveis com o número de lugares informado               | `filtrarVeiculosPorLugares()` em `ListaLocacao` |
| **Filtrar Por Categoria**           | Retorna veículos disponíveis de determinada categoria                       | `filtrarVeiculosPorCategoria()` em `ListaLocacao` |
| **Carregar Locações via CSV**       | Permitir leitura das locações a partir de arquivo CSV                        | `lerLocacoesCsv()` em `ListaLocacao` |
| **Validar Data da Locação**         | Impede locação com data de retirada posterior à devolução                    | `locarVeiculo()` em `ListaLocacao` |
| **Verificar Locação Ativa por Placa** | Verifica se há locação ativa de um veículo específico                       | `existeLocacaoAtivaPorPlaca()` em `ListaLocacao` |
| **Verificar Locação Ativa por Cliente** | Verifica se um cliente já possui locação ativa                              | `clientePossuiLocacaoAtiva()` em `ListaLocacao` |
| **Formatação de Exibição**          | Exibe as informações formatadas de forma amigável                            | `toString()` em `Locacao` |
| **Comparação de Locações**          | Define igualdade entre locações pelos mesmos cliente e veículo               | `equals()` em `Locacao` |

## 📋 Funcionalidades do Módulo Clientes

| Categoria                       | Funcionalidade                                                              | Onde está implementado                      |
|:--------------------------------|:----------------------------------------------------------------------------|:---------------------------------------------|
| **Cadastrar Cliente**            | Permitir o registro de um novo cliente, com validações de CPF, CNH, nome e telefone | `cadastrarCliente()` em `MenuClientes` |
| **Excluir Cliente**              | Permitir a exclusão de um cliente, se não possuir locações ativas            | `excluirCliente()` em `MenuClientes` e `removerCliente()` em `ListaCliente` |
| **Editar Nome do Cliente**       | Permitir a edição do nome de um cliente com validação                        | `editarNome()` em `MenuClientes` |
| **Editar CPF do Cliente**        | Permitir a edição do CPF com validação e sem permitir duplicação             | `editarCpf()` em `MenuClientes` |
| **Editar CNH do Cliente**        | Permitir a edição da CNH com validação                                        | `editarCnh()` em `MenuClientes` |
| **Editar Telefone do Cliente**   | Permitir a edição do telefone com validação                                   | `editarTelefone()` em `MenuClientes` |
| **Editar Cliente (Interface Completa)** | Interface interativa para edição de campos individuais                     | `editarCliente()` em `MenuClientes` |
| **Buscar Cliente por CPF**       | Localiza um cliente a partir de seu CPF                                        | `buscarPorCpf()` em `ListaCliente` |
| **Buscar Cliente por CNH**       | Localiza um cliente a partir da CNH                                           | `buscarPorCnh()` em `ListaCliente` |
| **Listar Clientes do Início**    | Lista clientes a partir do último até o primeiro (ordem reversa)              | `listarClientesDoInicio()` em `ListaCliente` |
| **Listar Clientes do Fim**       | Lista clientes a partir do primeiro até o último                              | `listarClientesDoFim()` em `ListaCliente` |
| **Carregar Clientes via CSV**    | Permitir a leitura dos clientes a partir de arquivo CSV                       | `lerClientesCsv()` em `ListaCliente` |
| **Comparar Clientes por CPF**    | Define igualdade entre clientes pelo CPF                                      | `equals()` em `Cliente` |
| **Formatação de Exibição**       | Exibe as informações formatadas de forma amigável                             | `toString()` em `Cliente` |


# Inputs válidos 

- Categoria: 
     
    - `1014` 
    - `SUV`

- Veículo: 

    - `BRA-8542`
    - `Creta`
    - `Hyundai`
    - `2020`
    - `120`
    - `5`
    - `SUV` 

- Cliente:
    - `345.678.910-22`
    - `Carlos Eduardo da Silva`
    - `12345678900`
    - `(55)988765432`
   
# Passo a passo 
- Menu principal: 
    - 3 - Gerenciar categorias 
    - Menu de caegoria 
        - 1 - cadastro de categoria
            - `1014` 
            - `SUV`
        - 4 - listar categorias 
        - 0 - voltar 
    - 2 - gerenciar veículos 
        - 1 cadastro de veículos
            - 1 - cadastro de veículos 
                - `BRA-8542`
                - `Creta`
                - `Hyundai`
                - `2020`
                - `120`
                - `5`
                - `SUV`  
            - 3 - Editar veículo 
                 - `BRA-8542`
                - `Creta`
                - `Hyundai`
                - `2022`
                - `130`
                - `5`
                - `SUV`  
            - 4 - listar veículos 
            - 0 - voltar 
    - 1 - Gerenciar clientes 
        - 1 - Cadastro de clientes 
            - `345.678.910-22`
            - `Carlos Eduardo da Silva`
            - `12345678900`
            - `(55)988765432`
         - 0 - voltar 
    - 4 - Gerenciar locações 
        - 1 - Locar veículo 
            - `BRA-8541`
            - `345.678.910-22`
            - `16/04/2025`
            - `17/04/2025`
            - `1000`
        - 2 - Devolver Veículo 
            - `BRA-8541`
        - 3 - Filtrar veiculos disponíveis 
            - `3`
            - `1`
            -  `SUV`
        - 4 - listar locações ativas 
        - 5 - Listar veículos disponíveis 
        - 0 - voltar 
        





    

