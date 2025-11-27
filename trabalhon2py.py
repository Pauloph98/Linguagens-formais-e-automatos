import sys

def main():
    # Definição dos Estados (usamos strings para facilitar em Python)
    state = "SAUDACAO"
    
    # Variáveis de fluxo
    nome_cliente = ""
    
    # Variáveis temporárias (para o item sendo escolhido agora)
    temp_burger = ""
    temp_descricao = ""
    temp_qtd = ""
    temp_ponto = ""
    
    # Variável acumuladora (Lista de tudo que foi pedido)
    lista_itens = "" 
    
    bebida = ""
    tipo_entrega = ""
    endereco = ""
    pagamento = ""

    print("--- Chatbot BoaHamburgueria (Python Version) ---")
    print("Bot: Olá! Digite 'começar' para fazer seu pedido.")

    while True:
        # Em Python, usamos input() para ler dados
        # .strip() remove espaços extras
        try:
            texto_input = input("> ").strip()
        except EOFError:
            break # Evita erro em alguns terminais online

        # --- LÓGICA DO AUTÔMATO (IF/ELIF agindo como Switch Case de Estados) ---
        
        if state == "SAUDACAO":
            if texto_input.lower() in ["começar", "comecar", "quero"]:
                state = "PEDIR_NOME"
                print("Bot: Ótimo! Primeiro, qual é o seu nome?")
            else:
                print("Bot: Digite 'começar' para iniciarmos.")

        elif state == "PEDIR_NOME":
            nome_cliente = texto_input
            state = "ESCOLHER_BURGER"
            print(f"Bot: Olá, {nome_cliente}!")
            mostrar_menu_burgers()

        elif state == "ESCOLHER_BURGER":
            opcao_valida = True
            if texto_input == "1":
                temp_burger = "X-Bacon"
                temp_descricao = "Pão brioche, carne 180g, queijo cheddar e muito bacon."
            elif texto_input == "2":
                temp_burger = "X-Salada"
                temp_descricao = "Pão, carne 160g, queijo prato, alface, tomate e maionese verde."
            elif texto_input == "3":
                temp_burger = "Smash Burger"
                temp_descricao = "Pão, 2 carnes prensadas de 80g, cheddar duplo e cebola caramelizada."
            elif texto_input == "4":
                temp_burger = "Artesanal"
                temp_descricao = "Pão australiano, carne 200g, queijo brie e geleia de pimenta."
            else:
                print("Bot: Opção inválida. Digite 1, 2, 3 ou 4.")
                opcao_valida = False

            if opcao_valida:
                state = "DETALHES_BURGER"
                print(f"\n--- DETALHES DO {temp_burger.upper()} ---")
                print(temp_descricao)
                print("----------------------------------")
                print("Deseja confirmar este lanche?")
                print("1 - SIM (Continuar) | 2 - NÃO (Escolher outro)")

        elif state == "DETALHES_BURGER":
            if texto_input == "1":
                state = "ESCOLHER_QUANTIDADE"
                print(f"Bot: Quantas unidades de {temp_burger} você quer?")
            elif texto_input == "2":
                state = "ESCOLHER_BURGER"
                print("Bot: Sem problemas. Escolha outra opção:")
                mostrar_menu_burgers()
            else:
                print("Bot: Digite 1 para Confirmar ou 2 para Voltar.")

        elif state == "ESCOLHER_QUANTIDADE":
            # Verifica se é número digitado
            if texto_input.isdigit():
                temp_qtd = texto_input
                state = "ESCOLHER_PONTO"
                print("Bot: Qual o ponto da carne para esses hambúrgueres?")
                print("1 - Mal passada | 2 - Ao ponto | 3 - Bem passada")
            else:
                print("Bot: Por favor, digite apenas números (ex: 1, 2).")

        elif state == "ESCOLHER_PONTO":
            ponto_valido = True
            if texto_input == "1": temp_ponto = "Mal passada"
            elif texto_input == "2": temp_ponto = "Ao ponto"
            elif texto_input == "3": temp_ponto = "Bem passada"
            else:
                print("Bot: Digite 1, 2 ou 3 para o ponto.")
                ponto_valido = False
            
            if ponto_valido:
                # ADICIONA O ITEM NA LISTA (Acumulador)
                lista_itens += f"- {temp_qtd}x {temp_burger} ({temp_ponto})\n"
                
                state = "ADICIONAR_MAIS"
                print("Bot: Item adicionado!")
                print("Deseja pedir MAIS ALGUM hambúrguer?")
                print("1 - SIM (Adicionar outro) | 2 - NÃO (Ir para bebidas)")

        elif state == "ADICIONAR_MAIS":
            if texto_input == "1":
                state = "ESCOLHER_BURGER" # LOOP
                print("Bot: Certo! Qual é o próximo lanche?")
                mostrar_menu_burgers()
            elif texto_input == "2":
                state = "ESCOLHER_BEBIDA"
                print("Bot: Ok. Vamos para as bebidas.")
                print("Deseja algo para beber? Digite o NÚMERO:")
                print("1 - Coca-Cola | 2 - Suco | 3 - Água | 4 - Sem bebida")
            else:
                print("Bot: Digite 1 para Sim ou 2 para Não.")

        elif state == "ESCOLHER_BEBIDA":
            if texto_input == "1": bebida = "Coca-Cola"
            elif texto_input == "2": bebida = "Suco"
            elif texto_input == "3": bebida = "Água"
            elif texto_input == "4": bebida = "Sem bebida"
            else:
                print("Bot: Escolha entre 1 e 4.")
                continue # Pula para o próximo loop

            state = "TIPO_ENTREGA"
            print("Bot: Como deseja receber o pedido?")
            print("1 - Entrega (Delivery) | 2 - Retirar no Local")

        elif state == "TIPO_ENTREGA":
            if texto_input == "1":
                tipo_entrega = "Entrega"
                state = "INFORMAR_ENDERECO"
                print("Bot: Certo. Digite seu endereço completo:")
            elif texto_input == "2":
                tipo_entrega = "Retirada"
                endereco = "Balcão da Loja"
                state = "CONFIRMAR"
                mostrar_resumo(nome_cliente, lista_itens, bebida, tipo_entrega, endereco)
            else:
                print("Bot: Digite 1 para Entrega ou 2 para Retirar.")

        elif state == "INFORMAR_ENDERECO":
            if len(texto_input) > 5:
                endereco = texto_input
                state = "CONFIRMAR"
                mostrar_resumo(nome_cliente, lista_itens, bebida, tipo_entrega, endereco)
            else:
                print("Bot: Endereço muito curto.")

        elif state == "CONFIRMAR":
            if texto_input == "1":
                state = "PAGAMENTO"
                print("Bot: Pagamento em Dinheiro (1) ou Cartão (2)?")
            elif texto_input == "2":
                lista_itens = "" # Zera a lista
                state = "ESCOLHER_BURGER"
                print("Bot: Lista limpa. Vamos recomeçar o pedido.")
                mostrar_menu_burgers()
            elif texto_input == "3":
                print("Bot: Pedido cancelado. Até logo!")
                sys.exit()
            else:
                print("Bot: Digite 1, 2 ou 3.")

        elif state == "PAGAMENTO":
            if "1" in texto_input or "dinheiro" in texto_input.lower():
                pagamento = "Dinheiro"
                state = "FINALIZADO"
            elif "2" in texto_input or "cart" in texto_input.lower():
                pagamento = "Cartão"
                state = "FINALIZADO"
            else:
                print("Bot: Opção inválida.")
            
            if state == "FINALIZADO":
                print("\n***********************************")
                print("      PEDIDO ENVIADO COM SUCESSO!    ")
                print("***********************************")
                print(f"Cliente: {nome_cliente}")
                print("--- ITENS ---")
                print(lista_itens.strip())
                print("-------------")
                print(f"Bebida: {bebida}")
                print(f"Modo: {tipo_entrega}")
                if tipo_entrega == "Entrega":
                    print(f"Endereço: {endereco}")
                print(f"Pagamento: {pagamento}")
                print("***********************************")
                break

# Função Auxiliar Menu
def mostrar_menu_burgers():
    print("Escolha seu lanche pelo NÚMERO:")
    print("1 - X-Bacon")
    print("2 - X-Salada")
    print("3 - Smash Burger")
    print("4 - Artesanal")

# Função Auxiliar Resumo
def mostrar_resumo(nome, itens, beb, tipo, end):
    print("\n--- CONFIRMAÇÃO DO PEDIDO ---")
    print(f"Cliente: {nome}")
    print("Itens:")
    print(itens.strip())
    print(f"Bebida: {beb}")
    print(f"Tipo: {tipo}")
    if tipo == "Entrega":
        print(f"Endereço: {end}")
    else:
        print("Local: Retirada no balcão")
    print("-----------------------------")
    print("1 - CONFIRMAR | 2 - REFAZER DO ZERO | 3 - CANCELAR")

if __name__ == "__main__":
    main()