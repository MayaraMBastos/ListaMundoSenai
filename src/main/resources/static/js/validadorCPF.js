function validarCPF(cpf) {
  // Remover caracteres não numéricos
  cpf = cpf.replace(/\D/g, '');

  // Verificar se o CPF tem 11 dígitos
  if (cpf.length !== 11) {
    return false;
  }

  // Verificar CPFs com todos os dígitos iguais (invalidos, mas passam na validação anterior)
  if (/^(\d)\1{10}$/.test(cpf)) {
    return false;
  }

  // Cálculo dos dígitos verificadores
  let sum = 0;
  for (let i = 0; i < 9; i++) {
    sum += parseInt(cpf.charAt(i)) * (10 - i);
  }
  let remainder = (sum * 10) % 11;
  if (remainder === 10 || remainder === 11) {
    remainder = 0;
  }
  if (remainder !== parseInt(cpf.charAt(9))) {
    return false;
  }

  sum = 0;
  for (let i = 0; i < 10; i++) {
    sum += parseInt(cpf.charAt(i)) * (11 - i);
  }
  remainder = (sum * 10) % 11;
  if (remainder === 10 || remainder === 11) {
    remainder = 0;
  }
  if (remainder !== parseInt(cpf.charAt(10))) {
    return false;
  }

  return true;
}