<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   
<head>
    <!--
    checked by http://validator.w3.org : "This page is valid XHTML 1.0 Transitional"
    -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Home</title>



    <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" href="style.ie6.css" type="text/css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" href="style.ie7.css" type="text/css" media="screen" /><![endif]-->

   <style type="text/css">
.post .layout-item-0 { padding-right: 0px;padding-left: 0px; }
.post .layout-item-1 { border-top-width:1px;border-top-style:solid;border-top-color:#DEDEDE; }
.post .layout-item-2 { padding: 10px; }
.post .layout-item-3 { padding: 5px; }
   .ie7 .post .layout-cell {border:none !important; padding:0 !important; }
   .ie6 .post .layout-cell {border:none !important; padding:0 !important; }
   </style>

</head>
<body>
<div id="main">
    <div class="cleared reset-box"></div>
<div class="bar nav">
<div class="nav-outer">
<div class="nav-wrapper">
<div class="nav-inner">
	<ul class="hmenu">
		<li>
			<a href="./index.html" class="active">BENGMAT</a>
		</li>	
		<li>
			<a href="./index.html">Biomaterials</a>
		</li>	
	</ul>
</div>
</div>
</div>
</div>
<div class="cleared reset-box"></div>
<div class="box sheet">
        <div class="box-body sheet-body">
            <div class="layout-wrapper">
                <div class="content-layout">
                    <div class="content-layout-row">
                        <div class="layout-cell content">
<div class="box post">
    <div class="box-body post-body">
<div class="post-inner article">
                                <div class="postcontent">
<div class="content-layout">
    <div class="content-layout-row">
    <div class="layout-cell layout-item-0" style="width: 100%;">
        <p><img src="./images/biotech.jpg" alt="an image" id="preview-image" name="preview-image" width="350" height="291" /></p>
        
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam pharetra, tellus sit amet congue vulputate, nisi erat iaculis nibh, vitae feugiat sapien ante eget mauris. </p>
        
        <p>Aenean sollicitudin imperdiet arcu, vitae dignissim est posuere id.</p>
        
        <p><a href="./index.html">Read more</a></p>
    </div>
    </div>
</div>
<div class="content-layout-br layout-item-1">
</div><div class="content-layout">
    <div class="content-layout-row">
    <div class="layout-cell layout-item-2" style="width: 100%;">
        <p>Etiam pharetra, tellus sit amet congue vulputate, nisi erat iaculis nibh, vitae feugiat sapien ante eget mauris. Aenean sollicitudin imperdiet arcu, vitae dignissim est posuere id.<br /></p>


   <p><b><xsl:value-of select="//name" /></b></p>
   <p><b>Contents: </b><xsl:value-of select="//content" /></p>
   <p><b>Paid authors:</b></p>
    <table border="1" width="100%">
      <tbody>
      <tr>
      <th>Product</th>
      <th>Price</th>
      <th>Available units</th>
      <xsl:for-each select="BiomaterialList/Biomaterial">
      <xsl:sort select="@name">
         <xsl:if test="available_units > 0">
      		</xsl:if>
      		</xsl:sort>
      		</xsl:for-each>
      		</tr>
      		<tr>
            <td><i><xsl:value-of select="@product_name"></xsl:value-of></i></td>
            <td><i><xsl:value-of select="@price_unit"></xsl:value-of></i></td>
            <td><i><xsl:value-of select="@available_units"></xsl:value-of></i></td>
            </tr>
	   </tbody>
   </table>
   
    </div>
</div>
<div class="content-layout">
    <div class="content-layout-row">
    <div class="layout-cell layout-item-2" style="width: 50%;">
        
        <div style="margin-left: 2em">         </div>
    </div><div class="layout-cell layout-item-2" style="width: 50%;">
        <p></p>


        <p></p>
    </div>
    </div>
</div>


                </div>
                <div class="cleared"></div>
                </div>

		<div class="cleared"></div>
    </div>
</div>

                          <div class="cleared"></div>
                        </div>
                        <div class="layout-cell sidebar1">
<div class="box block">
    <div class="box-body block-body">
                <div class="bar blockheader">
                    <h3 class="t"> Shopping List </h3>
                </div>
                <div class="box blockcontent">
                    <div class="box-body blockcontent-body">
                 <ul>
            <li> 3 units of plastic. </li>
        
            <li> 1 unit of ceramic.</li>
            
            <li> 25 units of titanium.</li>
            
            <li> 12 units of elastic biopolymer.</li>

        </ul>
<p><br /></p>


                                		<div class="cleared"></div>
                    </div>
                </div>
		<div class="cleared"></div>
    </div>
</div>

                          <div class="cleared"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="cleared"></div>
            <div class="footer">
                <div class="footer-body">
                            <div class="footer-text">
                                <p>Copyright © 2019. All Rights Reserved.</p>
                                <div class="cleared"></div>
                                <p class="page-footer">Designed by <a href="http://www.websitedesigndurban.com" target="_blank">www.websitedesigndurban.com</a>.</p>
                            </div>
                    <div class="cleared"></div>
                </div>
            </div>
    		<div class="cleared"></div>
        </div>
    </div>
    <div class="cleared"></div>
</div>

</body>
</html>


</xsl:template>

</xsl:stylesheet>