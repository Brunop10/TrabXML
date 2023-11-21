<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:nfe="http://www.portalfiscal.inf.br/nfe">

    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Notas Fiscais</title>
            </head>
            <body>
                <xsl:apply-templates select="//nfe:infNFe" mode="details"/>
            </body>
        </html>
    </xsl:template>


    <xsl:template match="nfe:infNFe" mode="details">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
                <title>Nota Fiscal <xsl:value-of select="nfe:ide/nfe:nNF"/></title>
            </head>
            <body>
                <h2>Nota Fiscal <xsl:value-of select="nfe:ide/nfe:nNF"/></h2>
                <h3>Detalhes dos Produtos</h3>
                <table border="1">
                    <tr>
                        <th>Data de compra</th>
                        <th>Total ISSQN retido</th>
                        <th>Total ICMS</th>
                        <th>Total tributos</th>
                        <th>Total frete</th>
                        <th>Descricao</th>
                        <th>Quantidade</th>
                        <th>Valor Unitario</th>
                        <th>Valor Total</th>
                    </tr>
                    <xsl:for-each select="nfe:det">
                        <tr>
                            <td><xsl:value-of select="../nfe:ide/nfe:dEmi"/></td>
                            <td><xsl:value-of select="nfe:imposto//nfe:ISSQN/nfe:vISSQN"/></td>
                            <td><xsl:value-of select="nfe:imposto//nfe:vICMS"/></td>
                            <td><xsl:value-of select="nfe:imposto//nfe:vTotTrib"/></td>
                            <td><xsl:value-of select="../nfe:total//nfe:vFrete"/></td>
                            <td><xsl:value-of select="nfe:prod/nfe:xProd"/></td>
                            <td><xsl:value-of select="nfe:prod/nfe:qCom"/></td>
                            <td><xsl:value-of select="nfe:prod/nfe:vUnCom"/></td>
                            <td><xsl:value-of select="nfe:prod/nfe:vProd"/></td>
                        </tr>
                    </xsl:for-each>
                </table>

                <h4>Valores totais da nota</h4>
                <table border="1">
                    <tr>
                        <th>vBC</th><th>vICMS</th><th>vProd</th>
                        <th>vII</th><th>vIPI</th><th>vPIS</th>
                        <th>vCOFINS</th><th>vOutro</th><th>vNF</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="nfe:total/nfe:ICMSTot/nfe:vBC"/></td>
                        <td><xsl:value-of select="nfe:total/nfe:ICMSTot/nfe:vICMS"/></td>
                        <td><xsl:value-of select="nfe:total/nfe:ICMSTot/nfe:vProd"/></td>
                        <td><xsl:value-of select="nfe:total/nfe:ICMSTot/nfe:vII"/></td>
                        <td><xsl:value-of select="nfe:total/nfe:ICMSTot/nfe:vIPI"/></td>
                        <td><xsl:value-of select="nfe:total/nfe:ICMSTot/nfe:vPIS"/></td>
                        <td><xsl:value-of select="nfe:total/nfe:ICMSTot/nfe:vCOFINS"/></td>
                        <td><xsl:value-of select="nfe:total/nfe:ICMSTot/nfe:vOutro"/></td>
                        <td><xsl:value-of select="nfe:total/nfe:ICMSTot/nfe:vNF"/></td>

                    </tr>
                </table>

                <h3>Detalhes da nota</h3>
                <h4>Info emissor</h4>
                <table border="1">
                    <tr>
                        <th>Nome</th><th></th>
                        <th>Lgr</th> <th>Nro</th> <th>Cpl</th> <th>Bairro</th>
                        <th>Mun</th> <th>Estado</th> <th>Pais</th> <th>E-mail</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="nfe:emit/nfe:xNome"/></td>
                        <td><xsl:value-of select="nfe:emit/nfe:xFant"/></td>
                        <td><xsl:value-of select="nfe:emit/nfe:enderEmit/nfe:xLgr"/></td>
                        <td><xsl:value-of select="nfe:emit/nfe:enderEmit/nfe:nro"/></td>
                        <td><xsl:value-of select="nfe:emit/nfe:enderEmit/nfe:xCpl"/></td>
                        <td><xsl:value-of select="nfe:emit/nfe:enderEmit/nfe:xBairro"/></td>
                        <td><xsl:value-of select="nfe:emit/nfe:enderEmit/nfe:xMun"/></td>
                        <td><xsl:value-of select="nfe:emit/nfe:enderEmit/nfe:UF"/></td>
                        <td><xsl:value-of select="nfe:emit/nfe:enderEmit/nfe:xPais"/></td>
                        <td><xsl:value-of select="nfe:emit/nfe:enderEmit/nfe:fone"/></td>
                    </tr>
                </table>

                <h4>Info destinatario</h4>
                <table border="1">
                    <tr>
                        <th>Nome</th>
                        <th>Lgr</th> <th>Nro</th> <th>Cpl</th> <th>Bairro</th>
                        <th>Mun</th> <th>Estado</th> <th>Pais</th> <th>Fone</th> <th>E-mail</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="nfe:dest/nfe:xNome"/></td>
                        <td><xsl:value-of select="nfe:dest/nfe:enderDest/nfe:xLgr"/></td>
                        <td><xsl:value-of select="nfe:dest/nfe:enderDest/nfe:nro"/></td>
                        <td><xsl:value-of select="nfe:dest/nfe:enderDest/nfe:xCpl"/></td>
                        <td><xsl:value-of select="nfe:dest/nfe:enderDest/nfe:xBairro"/></td>
                        <td><xsl:value-of select="nfe:dest/nfe:enderDest/nfe:xMun"/></td>
                        <td><xsl:value-of select="nfe:dest/nfe:enderDest/nfe:UF"/></td>
                        <td><xsl:value-of select="nfe:dest/nfe:enderDest/nfe:xPais"/></td>
                        <td><xsl:value-of select="nfe:dest/nfe:enderDest/nfe:fone"/></td>
                        <td><xsl:value-of select="nfe:dest/nfe:email"/></td>
                    </tr>
                </table>

                <h4>Info entrega</h4>
                <table border="1">
                    <tr>
                        <th>Lgr</th> <th>Nro</th> <th>Cpl</th> <th>Bairro</th>
                        <th>Mun</th> <th>Estado</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="nfe:entrega/nfe:xLgr"/></td>
                        <td><xsl:value-of select="nfe:entrega/nfe:nro"/></td>
                        <td><xsl:value-of select="nfe:entrega/nfe:xCpl"/></td>
                        <td><xsl:value-of select="nfe:entrega/nfe:xBairro"/></td>
                        <td><xsl:value-of select="nfe:entrega/nfe:xMun"/></td>
                        <td><xsl:value-of select="nfe:entrega/nfe:UF"/></td>
                    </tr>
                </table>

                <h4>Info transporte</h4>
                <table border="1">
                    <tr>
                        <th>Empresa</th> <th>Ender</th> <th>Mun</th> <th>Estado</th>
                        <th>qVol</th> <th>PesoL</th> <th>PesoB</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="nfe:transp/nfe:transporta/nfe:xNome"/></td>
                        <td><xsl:value-of select="nfe:transp/nfe:transporta/nfe:xEnder"/></td>
                        <td><xsl:value-of select="nfe:transp/nfe:transporta/nfe:xMun"/></td>
                        <td><xsl:value-of select="nfe:transp/nfe:transporta/nfe:UF"/></td>
                        <td><xsl:value-of select="nfe:transp/nfe:vol/nfe:qVol"/></td>
                        <td><xsl:value-of select="nfe:transp/nfe:vol/nfe:pesoL"/></td>
                        <td><xsl:value-of select="nfe:transp/nfe:vol/nfe:pesoB"/></td>
                    </tr>
                </table>

                <h4>Infos adicionais</h4>
                <table border="1">
                    <tr>
                        <th>Info adic</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="nfe:infAdic/nfe:infCpl"/></td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
