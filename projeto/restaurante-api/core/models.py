from django.db import models

class Restaurante(models.Model):
    id = models.AutoField(primary_key=True, null=False, verbose_name="Identificador")
    nome = models.TextField(null=False, blank=False)

    class Meta:
        verbose_name = "Restaurante"
        verbose_name_plural = "Restaurantes"

class Cliente(models.Model):
    id = models.AutoField(primary_key=True, null=False, verbose_name="Identificador")
    nome = models.TextField(null=False, blank=False)
    telefone = models.TextField(null=False, blank=False)
    email = models.TextField(null=False, blank=False)
    restaurante = models.ForeignKey(Restaurante, on_delete=models.DO_NOTHING, null=True, verbose_name="Restaurante")

    class Meta:
        verbose_name = "Cliente"
        verbose_name_plural = "Clientes"

class Endereço(models.Model):
    id = models.AutoField(primary_key=True, null=False, verbose_name="Identificador")
    numero = models.TextField(null=False, blank=False)
    cep = models.TextField(null=False, blank=False)
    referencia = models.TextField(null=False, blank=False)
    cliente = models.ForeignKey(Cliente, on_delete=models.DO_NOTHING, null=True, verbose_name="Cliente")
    restaurante = models.ForeignKey(Restaurante, on_delete=models.DO_NOTHING, null=True, verbose_name="Restaurante")

    class Meta:
        verbose_name = "Endereço"
        verbose_name_plural = "Endereços"

class Pedido(models.Model):
    id = models.AutoField(primary_key=True, null=False, verbose_name="Identificador")
    nome = models.TextField(null=False, blank=False)
    horario = models.TextField(null=False, blank=False)
    tipoad = models.TextField(null=False, blank=False)
    quantidade = models.TextField(null=False, blank=False)
    observacao = models.TextField(null=False, blank=False)
    cliente = models.ForeignKey(Cliente, on_delete=models.DO_NOTHING, null=True, verbose_name="Cliente")
    restaurante = models.ForeignKey(Restaurante, on_delete=models.DO_NOTHING, null=True, verbose_name="Restaurante")

    class Meta:
        verbose_name = "Pedido"
        verbose_name_plural = "Pedidos"

class Reserva(models.Model):
    id = models.AutoField(primary_key=True, null=False, verbose_name="Identificador")
    horario = models.TextField(null=False, blank=False)
    mesa = models.TextField(null=False, blank=False)
    quantidadechairs = models.TextField(null=False, blank=False)
    cliente = models.ForeignKey(Cliente, on_delete=models.DO_NOTHING, null=True, verbose_name="Cliente")

    class Meta:
        verbose_name = "Reserva"
        verbose_name_plural = "Reservas"

class Avaliacao(models.Model):
    id = models.AutoField(primary_key=True, null=False, verbose_name="Identificador")
    comentarios = models.TextField(null=False, blank=False)
    nota = models.TextField(null=False, blank=False)
    cliente = models.ForeignKey(Cliente, on_delete=models.DO_NOTHING, null=True, verbose_name="Cliente")

    class Meta:
        verbose_name = "Avaliação"
        verbose_name_plural = "Avaliações"






