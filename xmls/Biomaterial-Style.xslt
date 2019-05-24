<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">

<html>
    <p><b>Marketplace :</b></p>
<table border="1">
      <th>Product</th>
      <th>Price</th>
      <th>Available units</th>
      <xsl:for-each select="BiomaterialList/Biomaterial">
      <xsl:sort select="@name" />
         <xsl:if test="available_units &gt; 0">
      		<tr>
            <td><i><xsl:value-of select="@product_name" /></i></td>
            <td><i><xsl:value-of select="@price_unit" /></i></td>
            <td><i><xsl:value-of select="@available_units" /></i></td>
            </tr>
         </xsl:if>
      </xsl:for-each>
   </table>
</html>

</xsl:template>

</xsl:stylesheet>