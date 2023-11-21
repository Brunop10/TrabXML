<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:nfe="http://www.portalfiscal.inf.br/nfe">

    <xsl:output method="html" encoding="UTF-8"/>

    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="/">
        <html>
            <head>
                <title>Notas Fiscais</title>
            </head>
            <body>
                <xsl:call-template name="dashboard"/>
                <xsl:call-template name="notasLinks"/>
                <xsl:apply-templates select="//nfe:infNFe"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="dashboard">
        <div>
            <h2>Notas Fiscais</h2>
            <p>Numero de notas: <xsl:value-of select="count(//nfe:infNFe)"/></p>
            <p>Numero de produtos: <xsl:value-of select="count(//nfe:det)"/></p>
            <p>Valor total dos produtos: R$ <xsl:value-of select="sum(//nfe:total/nfe:ICMSTot/nfe:vProd)"/></p>
            <p>Valor total do ICMS: R$ <xsl:value-of select="sum(//nfe:total/nfe:ICMSTot/nfe:vICMS)"/></p>
            <p>Valor aproximado dos tributos: R$ <xsl:value-of select="sum(//nfe:total/nfe:ICMSTot/nfe:vTotTrib)"/></p>
            <p>Valor total de frete: R$ <xsl:value-of select="sum(//nfe:total/nfe:ICMSTot/nfe:vFrete)"/></p>
        </div>
    </xsl:template>

    <xsl:template name="notasLinks">
        <div>
            <h2>Lista de notas</h2>
            <ul>
                <xsl:for-each select="//nfe:infNFe">
                    <li>
                        <a href="{concat('output', position(), '.html')}">
                            <xsl:value-of select="nfe:ide/nfe:nNF"/>
                        </a>
                    </li>
                </xsl:for-each>
            </ul>
        </div>
    </xsl:template>

    <xsl:template match="nfe:infNFe">
        <div>
            <h2>Nota - <xsl:value-of select="nfe:ide/nfe:nNF"/></h2>
            <xsl:apply-templates select="." mode="details"/>
        </div>
    </xsl:template>

    <xsl:template match="nfe:infNFe" mode="details">
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
    </xsl:template>
</xsl:stylesheet>
