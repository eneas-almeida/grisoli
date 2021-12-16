const { env } = process;

interface IToken {
    tokenExpires: number;
    tokenSecret: string;
}

const token: IToken = {
    tokenExpires: env.TOKEN_EXPIRES ? Number(env.TOKEN_EXPIRES) : 3000,
    tokenSecret: env.TOKEN_SECRET || '57A28DBFB65685D05ABA506FF9728F85',
};

const { tokenExpires, tokenSecret } = token;

export default token;
export { tokenExpires, tokenSecret };
