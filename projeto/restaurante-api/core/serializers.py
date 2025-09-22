from rest_framework import serializers

from core import models


class ClienteSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Cliente
        fields = "__all__"

class RestauranteSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Restaurante
        fields = "__all__"

class EndereçoSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Endereço
        fields = "__all__"

class PedidoSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Pedido
        fields = "__all__"

class ReservaSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Reserva
        fields = "__all__"

class AvaliacaoSerializer(serializers.ModelSerializer):
    class Meta:
        model = models.Avaliacao
        fields = "__all__"