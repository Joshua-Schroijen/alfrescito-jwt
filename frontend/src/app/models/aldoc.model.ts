export interface AlDoc {
    id: string;
    owner: string;
    filePath: string;
    summary: string;
    namedEntities: { id: string; name: string }[];
}

export function emptyAlDoc(): AlDoc {
    return {
        id: "",
        owner: "",
        filePath: "",
        summary: "",
        namedEntities: []
    }
}