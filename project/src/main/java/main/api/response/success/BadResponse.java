package main.api.response.success;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadResponse extends OkResponse
{
    private List<String> errorList;

    public BadResponse(boolean success,  List<String> errorList)
    {
        super(success);
        this.errorList = errorList;
    }
}
