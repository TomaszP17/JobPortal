import React from 'react';
import {InputField, type InputFieldProps} from '../atoms/InputField';
import { Button } from '../atoms/Button';
import { Switch } from '../atoms/Switch';

export interface AuthFormProps {
    fields: InputFieldProps[];
    onSubmit: (e: React.FormEvent<HTMLFormElement>) => void;
    submitButtonLabel: string;
    showSwitch?: boolean;
    switchLabel?: string;
    switchState?: boolean;
    onSwitchChange?: (checked: boolean) => void;
}


export const AuthForm: React.FC<AuthFormProps> = ({
                                                      fields,
                                                      onSubmit,
                                                      submitButtonLabel,
                                                      showSwitch = false,
                                                      switchLabel = '',
                                                      switchState,
                                                      onSwitchChange,
                                                  }) => {
    return (
        <form onSubmit={onSubmit} className="space-y-4">
            {showSwitch && switchState !== undefined && onSwitchChange && (
                <Switch
                    id="accountType"
                    label={switchLabel}
                    checked={switchState}
                    onCheckedChange={onSwitchChange}
                />
            )}
            {fields.map((field: InputFieldProps) => (
                <InputField
                    key={field.id}
                    id={field.id}
                    label={field.label}
                    type={field.type}
                    placeholder={field.placeholder}
                    value={field.value}
                    onChange={field.onChange}
                    required={field.required}
                    disabled={field.disabled}
                />
            ))}
            <Button type="submit">{submitButtonLabel}</Button>
        </form>
    );
};
