const { env } = process;

const geral = {
    environment: env.NODE_ENV || 'development',
    port: env.SERVER_PORT || 3001,
    email: env.EMAIL_ADMIN || 'eneas.eng@yahoo.com',
};

const { environment, port, email } = geral;

export default geral;
export { environment, port, email };
