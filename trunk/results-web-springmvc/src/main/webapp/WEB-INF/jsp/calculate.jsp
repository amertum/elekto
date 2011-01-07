<%--
elekto provides voting and elections tools for small and medium enterprise. 
Copyright (C) 2010  http://www.elections-cedp.com

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <title>Elekto - Elections CE &amp; DP - Calcul des résultats</title>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="Content-language" content="fr" />
        
        <meta name="description" content="Service permettant de calculer les résultats d'attribution des élections de
            comité d'entreprise et délégués du personnel (CE & DP) et de fournir les résultats au format PDF Cerfa" />
        <meta name="keywords" content="elekto, election, depouillement, cedp, ce, dp, comite entreprise, delegues du personnel" />
        
        <link rel="stylesheet" href="/static/css/style.css" type="text/css" charset="UTF-8" media="screen" />
        
        <script type="application/javascript" src="/static/js/script.js" charset="UTF-8"></script>
    </head>
    <body>
        <h1>Calcul des résultats d'élections CE et DP</h1>
        <p>
            Ce service permet de calculer les résultats d'attribution des élections de Comité d'Entreprise (CE) et de
            Délégués du Personnel (DP) de façon simple à partir des résultats de comptage et de produire un procès verbal
            des résultats au format PDF <a href="http://vosdroits.service-public.fr/pme/R19702.xhtml" target="_blank">Cerfa</a>
            requis par l'inspection du travail.
        </p>
        <p>
            <strong>Ce service étant en cours de dévloppement, la justesse des résultats n'est pas garantie.</strong>
            Vous pouvez proposer des modifications via <a href="http://code.google.com/p/elekto/issues/list">l'outil de gestion de fiche d'anomalie</a>.
        </p>
        
        <p>
            Les étapes nécessaires sont les suivantes :
        </p>
        
        <ol>
            <li>
                Télécharger le modèle de fichier Excel : <a href="/results/elections-model.xls">elections-model.xls</a>
            </li>
            <li>
                Saisir les élections, les listes de candidats ainsi que les informations concernant les établissements.
                Vous pouvez consulter <a href="/results/help">l'aide de saisie du fichier excel</a>.
            </li>
            <li>
                <form:form id="electionsModelFileUploadForm" method="POST" action="/results/calculate" commandName="resultsInputs" enctype="multipart/form-data">
                    <fieldset>
                        <!-- TODO fieldError should disappear when field is edited, on-the-fly validation -->
                    
                        <div id="electionsModelFileField">
                            <label for="electionsModelFile">Envoyer le fichier Excel pour calculer les résultats et éditer les procès verbaux :</label>
                            <input type="file" name="electionsModelFile" />
                            <form:errors path="electionsModelFile" cssClass="fieldError" />
                        </div>
                        
                        <input type="submit" value="Envoyer" />
                    </fieldset>
                </form:form>
            </li>
            <li>
                Télécharger les résultats au format PDF Cerfa.
                          
                <p id="currentTourIndex">currentTourIndex : ${resultsProvider.operation.currentTour.index}</p>
                
                <ul>
                    <li>
                        <a href="/results/elekto-pv-cerfa-all-123456.pdf">Pdf Cerfa des résultats de toutes les élections</a>
                    </li>
                    <li>
                        <a href="/results/elekto-pv-cerfa-dp-123456.pdf">Pdf Cerfa des résultats des élections DP</a>
                    </li>
                    <li>
                        <a href="/results/elekto-pv-cerfa-ce-123456.pdf">Pdf Cerfa des résultats des élections CE</a>
                    </li>
                </ul>
            </li>
        </ol>
        
        <p id="footer">
            <a href="/static/licence/gpl-3.0-standalone.html" target="_blank">Licence GNU GPL</a>
            <a href="#" target="_blank">Mentions Légales</a>
            <a href="http://code.google.com/p/elekto/" target="_blank">Sources</a>
        </p>
    </body>
</html>
