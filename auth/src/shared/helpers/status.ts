const generateStatus = (error: boolean, code: number, message: string, payload?: any): any => {
    return payload && !Object.keys(payload).length ? { error, code, message } : { error, code, message, payload };
};

export { generateStatus };
