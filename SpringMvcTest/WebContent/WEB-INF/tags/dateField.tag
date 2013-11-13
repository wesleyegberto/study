<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ attribute name="id" required="true" %>
<%@ attribute name="value" required="false" %>

<input id="${id}" name="${id}" />
<script>
  $("#${id}").datepicker({dateFormat: 'dd/mm/yy'});
  if('${value}' > 10000) {
  	$('#${id}').datepicker('setDate', new Date(${value}));
  }
</script>