<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Closed Bets">
<#if bets??>
<table class="table table-striped">
  <thead>
    <tr>
      <th>Bet</th>
      <th>In Favor</th>
      <th>Against</th>
      <th>Winner</th>
    </tr>
  </thead>
  <tbody>
<#list bets as bet>
    <tr>
      <td><b>${bet.title}</b><br><i>${bet.description}</i></td>
      <td>${bet.initiator}</td>
      <td>${bet.challenger}</td>
      <td>${bet.winner}</td>
    </tr>
</#list>
  </tbody>
</table>
<#else>
<h2>No Bets available</h2>
<p>
Currently no bets are available. Want to add some?
</p>
</#if>
</@layout.masterTemplate>
