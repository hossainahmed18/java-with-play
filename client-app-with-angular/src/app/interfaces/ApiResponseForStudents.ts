export interface ApiResponseForStudents {
	readonly numberOfStudents: number,
    readonly fileName: string,
    readonly excelFileAsBase64 : string
}