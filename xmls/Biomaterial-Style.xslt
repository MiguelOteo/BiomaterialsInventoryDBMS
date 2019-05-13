<xsl:stylesheet version="2.0" xmlns:xsl="http://www.bengmat-store.org">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="//product_name" /></b></p>
   <p><b>BENGMAT</b></p>
   <table border="1">
      <th>Product</th>
      <th>Price</th>
      <th>Available units</th>
      <xsl:for-each select="Biomaterial">
      <xsl:sort select="@product_name" />
         <xsl:if test="available_units &gt; 0">
            <tr>
            <td><i><xsl:value-of select="@product_name" /></i></td>
            <td><xsl:value-of select="price_unit" /></td>
            <td><xsl:value-of select="available_units" /></td>
            <td><xsl:value-of select="@information" /></td>
            </tr>
         </xsl:if>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>
</xsl:stylesheet>