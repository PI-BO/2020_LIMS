<%@page import="config.Address" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>

<link rel="stylesheet" href="<%=Address.getNavigationMenuCSS()%>">
<div>
    <table id="navigation_table">
        <tr>
            <th class="navigation_table_header">Navigation</th>
        </tr>
        <tr class="navigation_table_row">
            <td class="navigation_table_data">
                <ul id="navigation_tree">

                    <li><span class="navigation_tree_node symbol_user">Account</span>
                        <ul class="navigation_tree_branches">
                            <li><span class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
                            <li><span class="navigation_tree_node symbol_briefcase" id="logout">Logout</span></li>
                        </ul>
                    </li>

                    <li><span class="navigation_tree_node symbol_user">Projektpartner</span>
                        <ul class="navigation_tree_branches">
                            <li><span class="navigation_tree_node symbol_clipboard">erstellen</span></li>
                            <li><span class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
                            <li><span class="navigation_tree_node symbol_search">suchen</span></li>
                        </ul>
                    </li>

                    <li><span class="navigation_tree_node symbol_cabinet" id="explorer_anzeigen">Explorer</span></li>

                    <li><span class="navigation_tree_node symbol_folder_closed">Projekte</span>
                        <ul class="navigation_tree_branches">
                            <li><span class="navigation_tree_node symbol_clipboard"
                                      id="projekt_erstellen">erstellen</span></li>
                        </ul>
                    </li>

                    <li><span class="navigation_tree_node symbol_folder_closed">Substanzen</span>
                        <ul class="navigation_tree_branches">
                            <li><span class="navigation_tree_node symbol_clipboard" id="probeneingang_erstellen">erstellen</span>
                            </li>
                            <!-- 							<li><span class="navigation_tree_node symbol_clipboard" id="substanz_erstellen">erstellen</span></li> -->
                            <li><span class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
                            <li><span class="navigation_tree_node symbol_search">suchen</span></li>
                        </ul>
                    </li>

                    <li><span class="navigation_tree_node symbol_folder_closed">Experiment</span>
                        <ul class="navigation_tree_branches">
                            <li><span class="navigation_tree_node symbol_clipboard"
                                      id="experiment_erstellen">erstellen</span></li>
                            <!-- 							<li><span class="navigation_tree_node symbol_clipboard" id="substanz_erstellen">erstellen</span></li> -->
                            <li><span class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
                            <li><span class="navigation_tree_node symbol_search">suchen</span></li>
                        </ul>
                    </li>

                    <li><span class="navigation_tree_node symbol_folder_closed">Analysen</span>
                        <ul class="navigation_tree_branches">
                            <li><span class="navigation_tree_node symbol_folder_closed">Eingangsanalyse</span>
                                <ul class="navigation_tree_branches">
                                    <li><span class="navigation_tree_node symbol_clipboard"
                                              id="eingangsanalytik_erstellen">erstellen</span></li>
                                </ul>
                            </li>
                            <li><span class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
                            <li><span class="navigation_tree_node symbol_search">suchen</span></li>
                        </ul>
                    </li>
                </ul>
            </td>
        </tr>
    </table>
</div>

<script>

    // 		Navigation komplett oeffnen
    $(".navigation_table_header").click(function () {

        if ($(".navigation_tree_branches").is(":hidden")) {

            $(".navigation_tree_branches").show(400);
            $(".symbol_folder_closed").show(400);
        } else {

            $(".navigation_tree_branches").hide(400);
        }
    });

    // 		init listener, zum oeffnen der einzelnen nodes
    $(".navigation_tree_node").click(function () {
        $(this).next().toggle(400);
        $(this).toggleClass("symbol_folder_open");
    });

    // 		init explorer
    $(document).ready(function () {
        var url = "<%=Address.getExplorerJSP()%>";
        var posting = $.post(url, {});
        posting.done(function (data) {
            $("#main-content-explorer").empty().append(data);
        });
    });

    $("#explorer_anzeigen").click(function () {
        var url = "<%=Address.getExplorerJSP()%>";
        var posting = $.post(url, {});
        posting.done(function (data) {
            $("#main-content-input-masks").hide();
            $("#main-content-explorer").show(500);
        });
    });

    $("#projekt_erstellen").click(function () {
        var url = "<%=Address.getProjektErstellenJsp()%>";
        var posting = $.post(url, {});
        posting.done(function (data) {
            $("#main-content-explorer").hide();
            $("#main-content-input-masks").hide();
            $("#main-content-input-masks").empty().append(data);
            $("#main-content-input-masks").show(500);
        });
    });

    $("#substanz_erstellen").click(function () {
        var url = "<%=Address.getSubstanzErstellenJsp()%>";
        var posting = $.post(url, {});
        posting.done(function (data) {
            $("#main-content-explorer").hide();
            $("#main-content-input-masks").hide();
            $("#main-content-input-masks").empty().append(data);
            $("#main-content-input-masks").show(500);
        });
    });

    $("#experiment_erstellen").click(function () {
        const url = "<%=Address.getExperimentErstellenJsp()%>";
        const posting = $.post(url, {});
        posting.done(function (data) {
            $("#main-content-explorer").hide();
            $("#main-content-input-masks").hide();
            $("#main-content-input-masks").empty().append(data);
            $("#main-content-input-masks").show(500);
        });
    });

    $("#experiment_erstellen").click(function () {
        const url = "<%=Address.getAnalyseErstellenJsp()%>";
        const posting = $.post(url, {});
        posting.done(function (data) {
            $("#main-content-explorer").hide();
            $("#main-content-input-masks").hide();
            $("#main-content-input-masks").empty().append(data);
            $("#main-content-input-masks").show(500);
        });
    });

    $("#probeneingang_erstellen").click(function () {
        var url = "<%=Address.getProbeneingangJSP()%>";
        var posting = $.post(url, {});
        posting.done(function (data) {
            $("#main-content-explorer").hide();
            $("#main-content-input-masks").hide();
            $("#main-content-input-masks").empty().append(data);
            $("#main-content-input-masks").show(500);
        });
    });

    $("#eingangsanalytik_erstellen").click(function () {
        var url = "<%=Address.getEingangsAnalytikJSP()%>";
        var posting = $.post(url, {});
        posting.done(function (data) {
            $("#main-content-explorer").hide();
            $("#main-content-input-masks").hide();
            $("#main-content-input-masks").empty().append(data);
            $("#main-content-input-masks").show(500);
        });
    });

    $("#logout").click(function () {
        var url = "<%=Address.getLoginHtml()%>";
        $(".navigation_tree_branches").hide(800);
        $("#main-content-explorer").hide(800);
        $("#main-content-input-masks").hide(800);
        setTimeout(function () {
            $(location).attr("href", url);
        }, 1000);
    });
</script>
