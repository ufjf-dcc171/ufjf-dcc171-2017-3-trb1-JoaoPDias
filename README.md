## 1º Trabalho - DCC171
##### Aluno: João Paulo Dias
##### Matrícula: 201576017
##### Curso: Sistemas de Informação

## Objetivo do Sistema
O sistema foi desenvolvido para gerenciar os produtos quanto ao seu nome e valor de venda e gerenciar mesas de um estabelecimento comercial quanto aos produtos pedidos na mesa, assim como o valor total da comanda. Oportunizando o fechamento do pedido, quando o cliente pede o total e deixa a mesa e gerando um resumo de informações sobre o pedido e seus itens. 

## Modelo de Dados
O modelo de dados está disponível _na raíz do Projeto_ e _no link_: [https://drive.google.com/file/d/0B2tDgEVXFkfUVEZRcER3OWVGMWc/view](https://drive.google.com/file/d/0B2tDgEVXFkfUVEZRcER3OWVGMWc/view "Modelo de Dados").

Ele consiste no uso de uma Classe Mesa que possui um id, uma descrição e N Pedidos que, por sua vez, possuem um id, uma descrição e N Itens, formados por um Produto(id, descrição e valor) e uma quantidade inteira.

## Campos Necessários para a Construção das Telas
Foram utilizadas JTables para serem repositórios de dados, gerir todas as opções de adição, remoção e edição de dados. JLabels, JTextFields e JCombobox para a inserção de dados do Produto e do Item do Pedido. O JCombobox foi estendido para a classe ProdutoCombobox que sobreescreve o método SetSelectedItem para selecionar de acordo com o id próprio do produto.

 Para a criação de mesas foi utilizado uma extensão do JButton, que tem como acréscimo uma instância de Mesa para cada um. 

Foram criados TableModels próprios para as tabelas de Produto, Pedido e Item do Pedido.

## Pontos importantes do funcionamento da interface
Inicialmente, para o funcionamento do sistema, devem ser incluídos produtos. Após incluí-los, será possível gerenciar as mesas e seus pedidos corretamente. Em seguida, na parte de gerenciamento de mesas é necessário incluir uma mesa e a partir dela incluir um novo pedido. Dentro da janela do pedido é possível gerenciar os produtos pedidos e suas quantidades. Não é possível ter mais de um pedido aberto para uma mesma mesa, como garantia de segurança e evitando erros de usabilidade no sistema. Enquanto o pedido não for fechado é possível adicionar itens a ele. Após o pedido fechado, é demonstrado um resumo do pedido e não é mais possível adicionar dados a ele.
## Pontos que apresentaram maior dificuldade de implementação
A implementação dos TableModels foi com um grau de dificuldade maior, exigiu mais tempo para poder conseguir operar corretamente a JTable, ainda mais para a edição de dados de um item, pois ao utilizar o combo , era necessário que no evento Value Changed da tabela de itens, o produto deveria ser demonstrado corretamente no Combobox, exigindo uma classe que herdasse do JCombobox e sobreescrevesse seu método de setar o item selecionado, utilizando o id do produto como referência.

## Pontos onde podem ser realizadas melhorias futuras

Devem ser feitas melhorias a cerca dos dados, utilizando um banco de dados para facilitar o uso e também permitir a criação de novas funcionalidades.

A interface necessita de melhorias quanto a implementação de atalhos, a sinalização de pedidos fechados na tabela de pedidos. O foco ao novo dado inserido na tabela, permitindo ao usuário a visualização do resultado de sua operação.
