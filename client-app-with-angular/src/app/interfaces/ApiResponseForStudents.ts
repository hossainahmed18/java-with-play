export interface ApiResponseForStudents {
	readonly numberOfStudents: number,
    readonly fileName: string,
    readonly csvfileAsBase64 : string
}